package com.example.daan.eindproject;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MovieInfoActivity extends AppCompatActivity {

    MovieInfo movieInfo;
    int removalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Filminfo");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        // extract movie info from intent
        Intent intent = getIntent();
        movieInfo = (MovieInfo) intent.getSerializableExtra("movieInfo");

        // show title and release year
        TextView titleView = findViewById(R.id.titleView);
        String releaseTitle = movieInfo.getReleaseTitle();
        titleView.setText(releaseTitle);

        // show plot
        TextView plotView = findViewById(R.id.plotView);
        String moviePlot = movieInfo.getMoviePlot();
        plotView.setText(moviePlot);

        // show poster
        ImageView posterView = findViewById(R.id.posterView);
        String posterUrl = movieInfo.getPosterUrl();
        String url = String.format("http://image.tmdb.org/t/p/w185/%s", posterUrl);
        Picasso.with(getApplicationContext()).load(url).into(posterView);

        // change add to watchlist button if user came from watchlist activity
        String fromWatchlist = intent.getStringExtra("fromWatchlist");
        if (fromWatchlist != null) {
            Button addWatchlist = findViewById(R.id.addWatchlist);
            addWatchlist.setText("verwijder uit watchlist");
        }
    }

    // move to filmlogactivity
    public void logFilm(View v) {

        Intent intent = new Intent(MovieInfoActivity.this, FilmLogActivity.class);

        // give movie title and poster to intent
        intent.putExtra("movieInfo", movieInfo);
        startActivity(intent);
    }

    // add or remove movie from watchlist
    public void changeWatchlist(View v) {

        // check if movie needs to be added or removed
        Button button = (Button) v;
        String text = button.getText().toString();
        if (text.equals("voeg toe aan watchlist")) {

            // DAAN IS TEST, MOET LATER VERVANGER WORDEN DOOR VARIABELE VOOR GEBRUIKERSNAAM
            String url = "https://ide50-danert.legacy.cs50.io:8080/daanwatchlist";
            RequestQueue queue = Volley.newRequestQueue(this);
            WatchlistPostRequest request = new WatchlistPostRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }, movieInfo);
            queue.add(request);
        }

        // remove from watchlist
        else {

            // check what the id of the movie is in database
            RequestQueue queue = Volley.newRequestQueue(this);

            // DAAN VERVANGEN DOOR GEBRUIKERSNAAM LATER!!!
            String url = "https://ide50-danert.legacy.cs50.io:8080/daanwatchlist";

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                // when watchlist was received
                @Override
                public void onResponse(JSONArray response) {

                    String movieId = movieInfo.getMovieId();

                    // check every entry for right id
                    for (int i = 0; i < response.length(); i++) {

                        try {
                            // grab entry
                            JSONObject movieEntry = response.getJSONObject(i);

                            // check if movie id is the right one
                            String checkId = movieEntry.getString("movieId");

                            // if right movie id, save id and stop searching
                            if (movieId.equals(checkId)) {

                                removalId = movieEntry.getInt("id");
                                break;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

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
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            queue.add(jsonArrayRequest);
        }

        // move back to homepage
        Intent intent = new Intent(MovieInfoActivity.this, HomepageActivity.class);
        startActivity(intent);
    }
}
