package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailsScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);
        getSupportActionBar().hide();
    }
}