package com.example.webserv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

/**
 * Created by ah_abdelhak on 11/1/2019.
 */

public class Chooce_Daily_Mon extends AppCompatActivity {

    CardView cv_daily, cv_week,cv_Monthly,cv_yearly;
    ImageView img_sign;
    TextView tv_sign, txtsignEng ;
    Bitmap bitmap;
    String Sign,sign_en;;

    SharedPreferences pref ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_daily_mon);

        img_sign=findViewById(R.id.img_sign);
        tv_sign=findViewById(R.id.txtsign);
        txtsignEng=findViewById(R.id.txtsignEng);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode

        Sign=pref.getString("signName", null); // getting String
        sign_en=pref.getString("signNameEng", null); // getting String

        txtsignEng.setText(sign_en);
        tv_sign.setText(Sign);


        Set_IMAGE();


        cv_daily=findViewById(R.id.cv_daily);
        cv_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent1 =new Intent(Chooce_Daily_Mon.this,Horoscope_result.class);
                intent1.putExtra("State","today");
                startActivity(intent1);

            }
        });

        cv_week=findViewById(R.id.cv_week);
        cv_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent1 =new Intent(Chooce_Daily_Mon.this,Horoscope_result.class);
                intent1.putExtra("State","week");
                startActivity(intent1);

            }
        });
        cv_Monthly=findViewById(R.id.cv_Monthly);
        cv_Monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent1 =new Intent(Chooce_Daily_Mon.this,Horoscope_result.class);
                intent1.putExtra("State","month");
                startActivity(intent1);

            }
        });
        cv_yearly=findViewById(R.id.cv_yearly);
        cv_yearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent1 =new Intent(Chooce_Daily_Mon.this,Horoscope_result.class);
                intent1.putExtra("State","year");
                startActivity(intent1);

            }
        });

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