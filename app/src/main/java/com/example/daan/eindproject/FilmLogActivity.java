package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FilmLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_log);

        // extract movie title from intent
        Intent intent = getIntent();
        String movieTitle = (String) intent.getSerializableExtra("movieTitle");

        TextView titleView = findViewById(R.id.titleView);
        titleView.setText(movieTitle);
    }
}
