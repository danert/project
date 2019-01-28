package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

        username = intent.getStringExtra("username");

        // show poster
        ImageView posterView = findViewById(R.id.posterView);
        String posterUrl = movieInfo.getPosterUrl();
        String url = String.format("http://image.tmdb.org/t/p/w185/%s", posterUrl);
        Picasso.with(getApplicationContext()).load(url).fit().into(posterView);

        removalId = 0;
    }

    // add film log to database
    public void submitLog(View v) {

        // https://stackoverflow.com/questions/5175728/how-to-get-the-current-date-time-in-java
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        // grab rating and review text
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        starRating = ratingBar.getRating();

        // make sure user enters rating
        if (starRating == 0) {
            Toast.makeText(getApplicationContext(), "Geef de film alsjeblieft een beoordeling!", Toast.LENGTH_LONG).show();
            return;
        }

        EditText reviewTextView = findViewById(R.id.reviewText);
        reviewText = reviewTextView.getText().toString();

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
        }, movieInfo, starRating, reviewText, timeStamp);
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

                        // remove movie from watchlist in database
                        String deleteUrl = String.format("https://ide50-danert.legacy.cs50.io:8080/%swatchlist/%d", username, removalId);
                        HelperClass.removeEntry(getApplicationContext(), deleteUrl);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // move back to homepage
                Intent intent = new Intent(FilmLogActivity.this, HomepageActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        checkQueue.add(jsonArrayRequest);
    }
}
