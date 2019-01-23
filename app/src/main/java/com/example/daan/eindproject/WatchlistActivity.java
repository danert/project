package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

public class WatchlistActivity extends AppCompatActivity {

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Watchlist");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        username = getIntent().getStringExtra("username");

        ListView watchlistView = findViewById(R.id.watchlist);

        showWatchlist();

        // set listener for listview
        watchlistView.setOnItemClickListener(new ListItemClickListener());
    }

    // listens if movie from watchlist is clicked
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // grabs movie that has been clicked
            MovieInfo movieInfo = (MovieInfo) parent.getItemAtPosition(position);

            // direct user to movie info activity
            Intent intent = new Intent(WatchlistActivity.this, MovieInfoActivity.class);
            intent.putExtra("movieInfo", movieInfo);
            intent.putExtra("username", username);
            intent.putExtra("fromWatchlist", "yes");
            startActivity(intent);
        }
    }

    // retrieve watchlist from database and show it to user
    public void showWatchlist() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("https://ide50-danert.legacy.cs50.io:8080/%swatchlist", username);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            // when watchlist was received
            @Override
            public void onResponse(JSONArray response) {

                // prepare list to give to adapter
                ArrayList<MovieInfo> watchlist = new ArrayList<MovieInfo>();

                // convert watchlist entries to movieinfos
                for (int i = 0; i < response.length(); i++) {

                    // create new movieinfo object
                    MovieInfo movie = new MovieInfo();

                    try {
                        // grab and set info of movie
                        JSONObject movieEntry = response.getJSONObject(i);

                        String moviePlot = movieEntry.getString("moviePlot");
                        movie.setMoviePlot(moviePlot);

                        String movieId = movieEntry.getString("movieId");
                        movie.setMovieId(movieId);

                        String posterUrl = movieEntry.getString("posterUrl");
                        movie.setPosterUrl(posterUrl);

                        String releaseTitle = movieEntry.getString("releaseTitle");
                        movie.setReleaseTitle(releaseTitle);

                        watchlist.add(movie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // show watchlist entries to user
                    ListView watchlistView = findViewById(R.id.watchlist);
                    PreviewAdapter adapter = new PreviewAdapter(getApplicationContext(), R.layout.filmpreview, watchlist);
                    watchlistView.setAdapter(adapter);
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
