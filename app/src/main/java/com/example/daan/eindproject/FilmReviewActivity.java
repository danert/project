package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FilmReviewActivity extends AppCompatActivity {

    String movieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Filmrecensie");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_review);

        // extract movie title from intent
        Intent intent = getIntent();
        movieTitle = (String) intent.getSerializableExtra("movieTitle");

        TextView titleView = findViewById(R.id.titleView);
        titleView.setText(movieTitle);
    }
}
