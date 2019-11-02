package com.example.webserv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Sign_choosen_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosce_sign);
        Sign_Model[] myListData = new Sign_Model[]{
                new Sign_Model("الحمل", "( من 21 مارس إلى 19 أبريل )",R.drawable.aries,"Aries"),
                new Sign_Model("الثور", "( من 20 أبريل إلى 20 مايو )",R.drawable.taurus,"taurus"),
                new Sign_Model("الجوزاء","( من 21 مايو إلى 20 يونيو )",R.drawable.gemini,"gemini"),
                new Sign_Model("السرطان", "( من 21 يونيو إلى 22 يوليو )" ,R.drawable.cancer,"cancer"),
                new Sign_Model("الاسد", "( من 23 يوليو إلى 22 أغسطس )",R.drawable.leo,"leo"),
                new Sign_Model("العذراء", "( من 23 أغسطس إلى 22 سبتمبر )",R.drawable.virgo,"virgo"),
                new Sign_Model("الميزان", "( 23 سبتمبر إلى 22 أكتوبر )",R.drawable.libra,"libra"),
                new Sign_Model("العقرب", "( من 23 أكتوبر إلى 21 نوفمبر )",R.drawable.scorpio,"scorpio"),
                new Sign_Model("القوس", "( من 22 نوفمبر إلى 21 ديسمبر )",R.drawable.sagittarius,"sagittarius"),
                new Sign_Model("الجدى", "( من 22 ديسمبر إلى 19 يناير )",R.drawable.capricorn,"capricorn"),
                new Sign_Model("الدلو", "( من 20 يناير إلى 18 فبراير )",R.drawable.aquarius,"aquarius"),
                new Sign_Model("الحوت","( من 19 فبراير إلى 20 مارس )" ,R.drawable.pisces,"pisces"),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(myListData,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}