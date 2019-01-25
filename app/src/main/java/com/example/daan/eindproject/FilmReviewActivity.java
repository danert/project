package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import org.w3c.dom.Text;

public class FilmReviewActivity extends AppCompatActivity {

    FilmReview filmReview;
    String username;
    int removalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Filmrecensie");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_review);

        // extract movie review and username
        Intent intent = getIntent();
        filmReview = (FilmReview) intent.getSerializableExtra("filmReview");
        username = (String) intent.getStringExtra("username");

        // if user is looking at profile of a friend, hide remove button
        if (intent.getStringExtra("fromFriends") != null) {
            Log.i("TEST", "AAAAAAAAAAAAAAAAAA");
            Button removeButton = findViewById(R.id.removeButton);
            removeButton.setVisibility(View.GONE);
        }

        // show releasetitle to user
        TextView titleView = findViewById(R.id.titleView);
        String releaseTitle = filmReview.getReleaseTitle();
        titleView.setText(releaseTitle);

        // show poster
        ImageView posterView = findViewById(R.id.posterView);
        String posterUrl = filmReview.getPosterUrl();
        String url = String.format("http://image.tmdb.org/t/p/w185/%s", posterUrl);
        Picasso.with(this).load(url).into(posterView);

        // show star rating
        RatingBar ratingBar = findViewById(R.id.ratingBar2);
        float starRating = filmReview.getStarRating();
        ratingBar.setRating(starRating);

        // show timestamp
        TextView timestampView = findViewById(R.id.timestampView);
        String timeStamp = filmReview.getTimeStamp();
        String year = timeStamp.substring(0,4);
        String month = timeStamp.substring(4, 6);
        String day = timeStamp.substring(6, 8);
        String date = String.format("Gekeken op %s-%s-%s", day, month, year);
        timestampView.setText(date);

        // show review text
        TextView reviewText = findViewById(R.id.reviewText);
        String textReview = filmReview.getReviewText();
        reviewText.setText(textReview);
    }

    // remove review from database
    public void removeReview(View v) {

        // check what the id of the review is in database
        RequestQueue queue = Volley.newRequestQueue(this);

        // set right url to look up specific movie
        String url = String.format("https://ide50-danert.legacy.cs50.io:8080/%sviewinghistory?timeStamp=%s", username, filmReview.getTimeStamp());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            // when movie was received
            @Override
            public void onResponse(JSONArray response) {

                try {
                    JSONObject review = response.getJSONObject(0);
                    removalId = review.getInt("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // request to delete review (https://www.itsalif.info/content/android-volley-tutorial-http-get-post-put)
                RequestQueue deleteQueue = Volley.newRequestQueue(getApplicationContext());
                String deleteUrl = String.format("https://ide50-danert.legacy.cs50.io:8080/%sviewinghistory/%d", username, removalId);
                StringRequest dr = new StringRequest(Request.Method.DELETE, deleteUrl,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), "Recensie verwijderd!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(FilmReviewActivity.this, HomepageActivity.class);
                                intent.putExtra("username", username);
                                startActivity(intent);
                                finish();
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
}
