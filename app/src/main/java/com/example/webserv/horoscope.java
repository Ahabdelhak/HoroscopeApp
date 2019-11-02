package com.example.webserv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

public class horoscope extends AppCompatActivity {


    TextView textView2,txtSign;

    String horoscope,Sign ,signNameEn;
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
        signNameEn=pref.getString("signNameEng", null); // getting String

        txtSign.setText(name);

        if(getIntent().hasExtra("signImage")) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("signImage"), 0, getIntent().getByteArrayExtra("signImage").length);
            img_sign.setImageBitmap(bitmap);
        }


        url_sign = "http://horoscope-api.herokuapp.com/horoscope/today/" + signNameEn + "";

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
                String message = null;
                if (volleyError instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (volleyError instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
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

                Toast.makeText(horoscope.this, "" + message, Toast.LENGTH_SHORT).show();
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

                                    textView2.setText(tr);
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
                            message = "The server could not be found. Please try again after some time!!";
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

                        Toast.makeText(horoscope.this, "" + message, Toast.LENGTH_SHORT).show();
                    }
                });
                Volley.newRequestQueue(getApplicationContext()).add(jsonObjReq1);
            }
        }




