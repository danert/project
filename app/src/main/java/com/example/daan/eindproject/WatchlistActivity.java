package com.example.daan.eindproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class WatchlistActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        // example list of movies for prototype, delete later
        ArrayList<String> exampleMovies = new ArrayList<String>();
        exampleMovies.add("Drive");
        exampleMovies.add("Her");
        exampleMovies.add("Enter the Void");
        exampleMovies.add("Lady Bird");

        ListView watchlistView = findViewById(R.id.watchlist);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.textview, R.id.textView6, exampleMovies);
        // test for prototype ends here

    }
}
