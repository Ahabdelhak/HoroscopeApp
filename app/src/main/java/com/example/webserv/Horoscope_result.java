package com.example.webserv;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by ah_abdelhak on 11/2/2019.
 */

public class Horoscope_result extends AppCompatActivity {


    TextView textView2,txtSign;

    String horoscope,Sign ,sign_en;
    String url_sign;
    String url;
    URI uri;
    ImageView img_sign ;
    SharedPreferences pref ;

    ProgressBar simpleProgressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope);
        // textView1=findViewById(R.id.textView3);
        textView2=findViewById(R.id.textView4);
        img_sign=findViewById(R.id.img_sign);
        txtSign=findViewById(R.id.signtxt);
        textView2.setMovementMethod(new ScrollingMovementMethod());

        simpleProgressBar= (ProgressBar) findViewById(R.id.simpleProgressBar);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode

        String name=pref.getString("signName", null); // getting String
        sign_en=pref.getString("signNameEng", null); // getting String

        txtSign.setText(name);

        Set_IMAGE(); //setImages

        String state= getIntent().getStringExtra("State");


        url_sign = "http://horoscope-api.herokuapp.com/horoscope/"+ state +"/" + sign_en + "";

        firstServiceCall(url_sign);

    }

    public void firstServiceCall(String url_sign){
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET, url_sign, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObject = null;
                        try {

                            horoscope=response.getString("horoscope");

//                            jsonObject = response.getJSONObject("horoscope");
//                            horoscope = jsonObject.getString("horoscope");
                            //textView1.setText(horoscope);


                            try {

                                uri = new URI(horoscope.replace(" ", "%20"));
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }

                            url = "http://api.mymemory.translated.net/get?q=" + uri + "&langpair=" + "en" + "|" + "ar" + "";
                            secondServiceCall(url);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                String message = null;
//                if (volleyError instanceof NetworkError) {
//                    message = "Cannot connect to Internet...Please check your connection!";
//                } else if (volleyError instanceof ServerError) {
//                    message = "The server could not be found. Please try again after some time!!";
//                } else if (volleyError instanceof AuthFailureError) {
//                    message = "Cannot connect to Internet...Please check your connection!";
//                } else if (volleyError instanceof ParseError) {
//                    message = "Parsing error! Please try again after some time!!";
//                } else if (volleyError instanceof NoConnectionError) {
//                    message = "Cannot connect to Internet...Please check your connection!";
//                } else if (volleyError instanceof TimeoutError) {
//                    message = "Connection TimeOut! Please check your internet connection.";
//                }
                simpleProgressBar.setVisibility(View.INVISIBLE);

              //  Toast.makeText(Horoscope_result.this, "" + message, Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjReq);
    }


    public void secondServiceCall(String url){

        JsonObjectRequest jsonObjReq1 = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = response.getJSONObject("responseData");
                            String tr = jsonObject.getString("translatedText");

                            if(tr.equals("QUERY LENGTH LIMIT EXCEDEED. MAX ALLOWED QUERY : 500 CHARS")){
                                textView2.setVisibility(View.VISIBLE);
                                textView2.setText(horoscope);
                            }else {
                                textView2.setVisibility(View.VISIBLE);
                                textView2.setText(tr);
                            }

                            simpleProgressBar.setVisibility(View.INVISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                String message = null;
                if (volleyError instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (volleyError instanceof ServerError) {
                    //message = "The server could not be found. Please try again after some time!!";
                    message = "";

                    textView2.setVisibility(View.VISIBLE);
                    textView2.setText(horoscope);

                } else if (volleyError instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (volleyError instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (volleyError instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (volleyError instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }
                simpleProgressBar.setVisibility(View.INVISIBLE);

                Toast.makeText(Horoscope_result.this, "" + message, Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjReq1);
    }


    public  void Set_IMAGE(){
        if(sign_en.equals("Aries")){
            img_sign.setImageResource(R.drawable.aries);
        }
        if(sign_en.equals("taurus")){
            img_sign.setImageResource(R.drawable.taurus);
        }
        if(sign_en.equals("gemini")){
            img_sign.setImageResource(R.drawable.gemini);
        }
        if(sign_en.equals("cancer")){
            img_sign.setImageResource(R.drawable.cancer);
        }
        if(sign_en.equals("leo")){
            img_sign.setImageResource(R.drawable.leo);
        }
        if(sign_en.equals("virgo")){
            img_sign.setImageResource(R.drawable.virgo);
        }
        if(sign_en.equals("libra")){
            img_sign.setImageResource(R.drawable.libra);
        }
        if(sign_en.equals("scorpio")){
            img_sign.setImageResource(R.drawable.scorpio);
        }
        if(sign_en.equals("sagittarius")){
            img_sign.setImageResource(R.drawable.sagittarius);
        }
        if(sign_en.equals("capricorn")){
            img_sign.setImageResource(R.drawable.capricorn);
        }

        if(sign_en.equals("aquarius")){
            img_sign.setImageResource(R.drawable.aquarius);
        }
        if(sign_en.equals("pisces")){
            img_sign.setImageResource(R.drawable.pisces);
        }

    }

}
