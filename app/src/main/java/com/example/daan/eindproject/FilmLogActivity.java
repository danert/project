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
    String reviewText;
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
        // DAAN IS TEST, MOET LATER VERVANGER WORDEN DOOR VARIABELE VOOR GEBRUIKERSNAAM
        String url = "https://ide50-danert.legacy.cs50.io:8080/daanviewinghistory";
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

        // test delay to make sure progressbar has time to update
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // if movie in watchlist, remove it from watchlist
        if (checkWatchlist() != 0) {

            Log.i("AJDHAHDKBAWKUDGWAUGDAWUDWAGYY", "AIUDHAWUIWUIWADH");

            // request to delete movie entry (https://www.itsalif.info/content/android-volley-tutorial-http-get-post-put)
            RequestQueue deleteQueue = Volley.newRequestQueue(getApplicationContext());

            String deleteUrl = String.format("https://ide50-danert.legacy.cs50.io:8080/daanwatchlist/%d", removalId);

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

        // move back to homepage
        Intent intent = new Intent(FilmLogActivity.this, HomepageActivity.class);
        startActivity(intent);
    }

    // check if movie is in watchlist, if it is it returns the corresponding id, if it isn't it returns 0
    public int checkWatchlist() {

        removalId = 0;

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        // DAAN VERVANGEN DOOR GEBRUIKERSNAAM LATER!!!
        String url = "https://ide50-danert.legacy.cs50.io:8080/daanwatchlist";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            // when watchlist was received
            @Override
            public void onResponse(JSONArray response) {

                String movieId = movieInfo.getMovieId();

                // check every entry
                for (int i = 0; i < response.length(); i++) {

                    try {

                        // grab entry
                        JSONObject movieEntry = response.getJSONObject(i);

                        // grab movie id that needs to be checked
                        String checkId = movieEntry.getString("movieId");

                        // if right movie id, stop searching
                        if (movieId.equals(checkId)) {

                            removalId = movieEntry.getInt("id");
                            break;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonArrayRequest);

        Log.i("removalId", Integer.toString(removalId));
        return removalId;
    }
}
