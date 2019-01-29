package com.example.daan.eindproject;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class WatchlistPostRequest extends StringRequest {

    // releaseTitle = movie title + releaseyear (e.g. Her (2013))
    String movieId, posterUrl, releaseTitle, moviePlot;

    // Constructor
    public WatchlistPostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, MovieInfo movieInfo) {
        super(method, url, listener, errorListener);

        movieId = movieInfo.getMovieId();
        posterUrl = movieInfo.getPosterUrl();
        moviePlot = movieInfo.getMoviePlot();
        releaseTitle = movieInfo.getReleaseTitle();
    }

    // Method to supply parameters to the request
    @Override
    protected Map<String, String> getParams() {

        Map<String, String> params = new HashMap<>();
        params.put("movieId", movieId);
        params.put("releaseTitle", releaseTitle);
        params.put("posterUrl", posterUrl);
        params.put("moviePlot", moviePlot);
        return params;
    }
}