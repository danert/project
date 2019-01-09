package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MovieInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
    }

    // move to filmlogactivity
    public void logFilm() {

        Intent intent = new Intent(MovieInfoActivity.this, FilmLogActivity.class);

        // give movie title and poster to intent
        intent.putExtra("title", title);
        intent.putExtra("poster", poster);

        startActivity(intent);
    }
}
