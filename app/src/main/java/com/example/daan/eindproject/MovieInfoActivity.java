package com.example.daan.eindproject;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

public class MovieInfoActivity extends AppCompatActivity {

    String movieTitle;
    MovieInfo movieInfo;

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
        movieTitle = movieInfo.getMovieTitle();
        String releaseYear = movieInfo.getReleaseYear();
        String titleRelease = String.format("%s (%s)", movieTitle, releaseYear);
        titleView.setText(titleRelease);

        // show plot
        TextView plotView = findViewById(R.id.plotView);
        String moviePlot = movieInfo.getMoviePlot();
        plotView.setText(moviePlot);

        // show poster
        ImageView posterView = findViewById(R.id.posterView);
        String posterUrl = movieInfo.getPosterUrl();
        String url = String.format("http://image.tmdb.org/t/p/w185/%s", posterUrl);
        Picasso.with(getApplicationContext()).load(url).into(posterView);

    }

    // move to filmlogactivity
    public void logFilm(View v) {

        Intent intent = new Intent(MovieInfoActivity.this, FilmLogActivity.class);

        // give movie title and poster to intent
        intent.putExtra("movieTitle", movieTitle);

        startActivity(intent);
    }

    // add movie to watchlist
    public void addWatchlist(View v) {

        // VOORBEELDNAAM VAN PROFIEL, LATER VERVANGEN
        String url = "https://ide50-danert.legacy.cs50.io:8080/watchlist";
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

        // move back to homepage
        Intent intent = new Intent(MovieInfoActivity.this, HomepageActivity.class);
        startActivity(intent);

    }


}
