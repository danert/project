package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MovieInfoActivity extends AppCompatActivity {

    String movieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        // extract movie title from intent
        Intent intent = getIntent();
        movieTitle = (String) intent.getSerializableExtra("movieTitle");

        TextView titleView = findViewById(R.id.titleView);
        titleView.setText(movieTitle);
    }

    // move to filmlogactivity
    public void logFilm(View v) {

        Intent intent = new Intent(MovieInfoActivity.this, FilmLogActivity.class);

        // give movie title and poster to intent
        intent.putExtra("movieTitle", movieTitle);

        startActivity(intent);
    }
}
