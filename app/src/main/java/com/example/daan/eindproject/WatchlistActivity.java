package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class WatchlistActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Watchlist");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        // example list of movies for prototype, delete later
        ArrayList<String> exampleMovies = new ArrayList<String>();
        exampleMovies.add("Drive");
        exampleMovies.add("Her");
        exampleMovies.add("Enter the Void");
        exampleMovies.add("Lady Bird");
        exampleMovies.add("It Follows");

        ListView watchlistView = findViewById(R.id.watchlist);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.textview, R.id.textView6, exampleMovies);

        watchlistView.setAdapter(arrayAdapter);
        // test for prototype ends here

        // set listener for listview
        watchlistView.setOnItemClickListener(new ListItemClickListener());

    }

    // listens if movie from watchlist is clicked
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // grabs movie title that has been clicked
            String movieTitle = (String) parent.getItemAtPosition(position);

            // direct user to movie movie info activity
            Intent intent = new Intent(WatchlistActivity.this, MovieInfoActivity.class);
            intent.putExtra("movieTitle", movieTitle);
            startActivity(intent);
        }
    }
}
