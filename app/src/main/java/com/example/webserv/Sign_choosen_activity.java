package com.example.webserv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Sign_choosen_activity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosce_sign);

        // data to populate the RecyclerView with
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("الحمل");
        animalNames.add("الحمل");
        animalNames.add("الحمل");
        animalNames.add("الحمل");
        animalNames.add("الحمل ");
        animalNames.add("الحمل");
        animalNames.add("الحمل");
        animalNames.add("الحمل");
        animalNames.add("الحمل");
        animalNames.add("الحمل");
        animalNames.add("الحمل");
        animalNames.add("الحمل");


        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,horoscope.class);
        startActivity(intent);
    }
}
