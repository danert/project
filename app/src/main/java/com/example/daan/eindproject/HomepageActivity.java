package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // change action bar title (https://stackoverflow.com/questions/3438276/how-to-change-the-text-on-the-action-bar)
        getSupportActionBar().setTitle("Cinemaster");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        showProgress();
    }

    public void pageClicked(View v) {

        TextView pageName = (TextView) v;

        // check what page has been clicked
        Intent intent = new Intent(HomepageActivity.this, ProfileActivity.class);

        switch (pageName.getText().toString()) {
            case "Kijkgeschiedenis": intent = new Intent(HomepageActivity.this, ViewingHistoryActivity.class);
                break;
            case "Watchlist": intent = new Intent(HomepageActivity.this, WatchlistActivity.class);
                break;
            case "Zoek een film": intent = new Intent(HomepageActivity.this, FilmSearchActivity.class);
                break;
        }

        // go to page
        startActivity(intent);
    }

    // fills in progress bar
    public void showProgress() {

        // grab user progress from database (DAAN NOG VERANDEREN NAAR USERNAME LATER)
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://ide50-danert.legacy.cs50.io:8080/daanprogression";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            // when viewing history was received
            @Override
            public void onResponse(JSONArray response) {

                try {
                    // get amount of movies watched
                    JSONObject userProgression = response.getJSONObject(0);
                    int moviesWatched = Integer.valueOf(userProgression.getString("moviesWatched"));

                    // calculate and show level (USERNAME NOG VERANDEREN)
                    int userLevel = (moviesWatched / 10) + 1;
                    TextView profileText = findViewById(R.id.profileText);
                    profileText.setText(String.format("Daan (lvl %d)", userLevel));

                    // show remaining progress to level up in progressbar
                    int moviesSingleDigit = moviesWatched % 10;
                    ProgressBar progressBar = findViewById(R.id.progressBar);
                    progressBar.setProgress(moviesSingleDigit);

                    // show how many movies user needs to watch to level up
                    int nextLevel = (userLevel) * 10;
                    TextView moviesLeft = findViewById(R.id.moviesLeft);
                    moviesLeft.setText(String.format("%d/%d", moviesWatched, nextLevel));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonArrayRequest);

    }
}
