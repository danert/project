package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FilmLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Log een film");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_log);

        // extract movie title from intent
        Intent intent = getIntent();
        String movieTitle = (String) intent.getSerializableExtra("movieTitle");

        TextView titleView = findViewById(R.id.titleView);
        titleView.setText(movieTitle);
    }

    // add film log to database
    public void submitLog(View v) {



        // move back to homepage
        Intent intent = new Intent(FilmLogActivity.this, HomepageActivity.class);
        startActivity(intent);
    }
}
