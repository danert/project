package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class MovieInfoActivity extends AppCompatActivity {

    MovieInfo movieInfo;
    int removalId;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Filminfo");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        // extract movie info and username from intent
        Intent intent = getIntent();
        movieInfo = (MovieInfo) intent.getSerializableExtra("movieInfo");
        username = intent.getStringExtra("username");

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

    // add or remove movie from watchlist
    public void changeWatchlist(View v) {

        // check if movie needs to be added or removed
        Button button = (Button) v;
        String text = button.getText().toString();
        String url = String.format("https://ide50-danert.legacy.cs50.io:8080/%swatchlist", username);

        // add movie to watchlist
        if (text.equals("voeg toe aan watchlist")) {

            // add movie to watchlist
            RequestQueue addQueue = Volley.newRequestQueue(this);
            WatchlistPostRequest request = new WatchlistPostRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Film toegevoegd aan watchlist!", Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }, movieInfo);
            addQueue.add(request);
        }

        // remove from watchlist
        else {

            // check what the id of the movie is in database
            RequestQueue queue = Volley.newRequestQueue(this);
            url = String.format("https://ide50-danert.legacy.cs50.io:8080/%swatchlist?movieId=%s", username, movieInfo.getMovieId());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                // when movie was received
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject movie = response.getJSONObject(0);
                        removalId = movie.getInt("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // delete movie from watchlist in database
                    String deleteUrl = String.format("https://ide50-danert.legacy.cs50.io:8080/%swatchlist/%d", username, removalId);
                    HelperClass.removeEntry(getApplicationContext(), deleteUrl);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            queue.add(jsonArrayRequest);

            Toast.makeText(getApplicationContext(), "Film verwijderd uit watchlist!", Toast.LENGTH_LONG).show();
        }

        // move back to homepage
        Intent intent = new Intent(MovieInfoActivity.this, HomepageActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    // add log icon to action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.log, menu);
        return true;
    }

    // move to film log activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(MovieInfoActivity.this, FilmLogActivity.class);
        intent.putExtra("movieInfo", movieInfo);
        intent.putExtra("username", username);
        startActivity(intent);
        return true;
    }
}
