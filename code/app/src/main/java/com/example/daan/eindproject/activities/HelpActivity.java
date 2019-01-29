package com.example.daan.eindproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.daan.eindproject.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setTitle("Uitleg");
    }
}
