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

public class ViewingHistoryActivity extends AppCompatActivity {

    ListView viewHistoryList;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Kijkgeschiedenis");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing_history);

        username = getIntent().getStringExtra("username");

        viewHistoryList = findViewById(R.id.viewhistoryList);
        getViewingHistory();

        viewHistoryList.setOnItemClickListener(new ListItemClickListener());
    }

    // listens if movie from view history is clicked
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // grabs movie that has been clicked
            FilmReview filmReview = (FilmReview) parent.getItemAtPosition(position);

            // direct user to movie review activity
            Intent intent = new Intent(ViewingHistoryActivity.this, FilmReviewActivity.class);
            intent.putExtra("filmReview", filmReview);
            startActivity(intent);
        }
    }

    // grabs viewing history of user from database
    public void getViewingHistory() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("https://ide50-danert.legacy.cs50.io:8080/%sviewinghistory", username);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            // when viewing history was received
            @Override
            public void onResponse(JSONArray response) {


                // prepare list to give to adapter
                ArrayList<FilmReview> filmReviews = new ArrayList<>();

                // convert database entries to filmreviews
                for (int i = 0; i < response.length(); i++) {

                    // create new filmreview object
                    FilmReview filmReview = new FilmReview();

                    try {

                        JSONObject databaseEntry = response.getJSONObject(i);

                        String movieId = databaseEntry.getString("movieId");
                        filmReview.setMovieId(movieId);

                        String posterUrl = databaseEntry.getString("posterUrl");
                        filmReview.setPosterUrl(posterUrl);

                        String releaseTitle = databaseEntry.getString("releaseTitle");
                        filmReview.setReleaseTitle(releaseTitle);

                        String reviewText = databaseEntry.getString("reviewText");
                        filmReview.setReviewText(reviewText);

                        String starRating = databaseEntry.getString("starRating");
                        float floatRating = Float.valueOf(starRating);
                        filmReview.setStarRating(floatRating);

                        filmReviews.add(filmReview);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // show viewing history to user
                    ReviewAdapter adapter = new ReviewAdapter(getApplicationContext(), R.layout.filmreview, filmReviews);
                    viewHistoryList.setAdapter(adapter);
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
