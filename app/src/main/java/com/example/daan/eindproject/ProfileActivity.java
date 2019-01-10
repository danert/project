package com.example.daan.eindproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Profiel");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}
