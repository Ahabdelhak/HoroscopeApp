package com.example.webserv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
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


    EditText ed;
    TextView textView1,textView2;

    String horoscope ;
    String url_sign;
    String url;
    URI uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope);

        ed=findViewById(R.id.editText4);
        textView1=findViewById(R.id.textView3);
        textView2=findViewById(R.id.textView4);
    }

    public void Search(View view) {
        String sign = ed.getText().toString();
        //url_sign = "https://widgets.fabulously40.com/horoscope.json?sign=" + sign + "";
        url_sign = "http://horoscope-api.herokuapp.com/horoscope/today/" + sign + "";

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
                            textView1.setText(horoscope);


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
            public void onErrorResponse(VolleyError error) {
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

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                Volley.newRequestQueue(getApplicationContext()).add(jsonObjReq1);
            }
        }




