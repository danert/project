package com.example.daan.eindproject.postrequests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.daan.eindproject.classes.MovieInfo;

import java.util.HashMap;
import java.util.Map;

public class ViewingHistoryPostRequest extends StringRequest {

    // releaseTitle = movie title + releaseyear (e.g. Her (2013))
    String movieId, posterUrl, releaseTitle, reviewText, starRating, timeStamp;

    // Constructor
    public ViewingHistoryPostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, MovieInfo movieInfo, float starRating, String reviewText, String timeStamp) {
        super(method, url, listener, errorListener);

        movieId = movieInfo.getMovieId();
        posterUrl = movieInfo.getPosterUrl();
        releaseTitle = movieInfo.getReleaseTitle();
        this.starRating = Float.toString(starRating);
        this.reviewText = reviewText;
        this.timeStamp = timeStamp;
    }

    // Method to supply parameters to the request
    @Override
    protected Map<String, String> getParams() {

        Map<String, String> params = new HashMap<>();
        params.put("movieId", movieId);
        params.put("releaseTitle", releaseTitle);
        params.put("posterUrl", posterUrl);
        params.put("reviewText", reviewText);
        params.put("starRating", starRating);
        params.put("timeStamp", timeStamp);
        return params;
    }
}
