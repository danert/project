package com.example.daan.eindproject;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class FilmLogActivity extends AppCompatActivity {

    MovieInfo movieInfo;
    float starRating;
    String reviewText, username;
    int removalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Log een film");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_log);

        // extract movie info and set title
        Intent intent = getIntent();
        movieInfo = (MovieInfo) intent.getSerializableExtra("movieInfo");
        TextView titleView = findViewById(R.id.titleView);
        titleView.setText(movieInfo.getReleaseTitle());

        // show poster
        ImageView posterView = findViewById(R.id.posterView);
        String posterUrl = movieInfo.getPosterUrl();
        String url = String.format("http://image.tmdb.org/t/p/w185/%s", posterUrl);
        Picasso.with(getApplicationContext()).load(url).fit().into(posterView);

        removalId = 0;
    }

    // add film log to database
    public void submitLog(View v) {

        // grab rating and review text
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        starRating = ratingBar.getRating();

        // make sure user enters rating
        if (starRating == 0) {
            Toast.makeText(getApplicationContext(), "Please give the movie a rating!", Toast.LENGTH_LONG).show();
            return;
        }

        EditText reviewTextView = (EditText) findViewById(R.id.reviewText);
        reviewText = reviewTextView.getText().toString();

        // make sure user enters text
        if (reviewText.length() == 0) {
            Toast.makeText(getApplicationContext(), "Please give the movie a review!", Toast.LENGTH_SHORT).show();
            return;
        }

        // add film log to database
        String url = String.format("https://ide50-danert.legacy.cs50.io:8080/%sviewinghistory", username);
        RequestQueue queue = Volley.newRequestQueue(this);
        ViewingHistoryPostRequest request = new ViewingHistoryPostRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, movieInfo, starRating, reviewText);
        queue.add(request);

        // check if movie is in watchlist
        RequestQueue checkQueue = Volley.newRequestQueue(this);

        // set right url to look up specific movie
        url = String.format("https://ide50-danert.legacy.cs50.io:8080/%swatchlist?movieId=%s", username, movieInfo.getMovieId());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                try {

                    // if movie in watchlist, remove it
                    if (response.length() != 0) {

                        JSONObject movie = response.getJSONObject(0);
                        removalId = movie.getInt("id");
                        deleteEntry();
                    }
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
        checkQueue.add(jsonArrayRequest);

        // test delay to make sure progressbar has time to update
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // move back to homepage
        Intent intent = new Intent(FilmLogActivity.this, HomepageActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    // remove movie from watchlist
    public void deleteEntry () {

        // request to delete movie entry (https://www.itsalif.info/content/android-volley-tutorial-http-get-post-put)
        RequestQueue deleteQueue = Volley.newRequestQueue(getApplicationContext());

        String deleteUrl = String.format("https://ide50-danert.legacy.cs50.io:8080/%swatchlist/%d", username, removalId);

        StringRequest dr = new StringRequest(Request.Method.DELETE, deleteUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        deleteQueue.add(dr);
    }
}
