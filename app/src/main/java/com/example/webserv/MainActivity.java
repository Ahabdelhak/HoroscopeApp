package com.example.webserv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    EditText ed, edfrom, edto;
    TextView textView2;

    URI uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = findViewById(R.id.editText);

        textView2=findViewById(R.id.textView2);

        edfrom = findViewById(R.id.editText2);
        edto = findViewById(R.id.editText3);


    }

    public void Translate(View view) {

        String word = ed.getText().toString();
        String from = edfrom.getText().toString();
        String to = edto.getText().toString();


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        try {

            uri = new URI(word.replace(" ", "%20"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        String url = "http://api.mymemory.translated.net/get?q=" + uri + "&langpair=" + from + "|" + to + "";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject jsonObject = response.getJSONObject("responseData");

                            String tr = jsonObject.getString("translatedText");

                            textView2.setText(tr);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }
}
