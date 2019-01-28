package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class FriendDetailActivity extends AppCompatActivity {

    ListView listView;
    String username, friendName;
    int removalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Vrienden");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);

        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new ListItemClickListener());

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        friendName = intent.getStringExtra("friendName");

        showFriend();
    }

    // show viewing history and progress of friend
    public void showFriend() {

        // request viewing history from database
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("https://ide50-danert.legacy.cs50.io:8080/%sviewinghistory", friendName);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                // prepare list to give to adapter
                ArrayList<FilmReview> filmReviews = new ArrayList<>();

                // get amount of movies watched
                int moviesWatched = response.length();

                // calculate and show level
                int userLevel = (moviesWatched / 10) + 1;
                TextView profileText = findViewById(R.id.profileText);
                profileText.setText(String.format("%s (lvl %d)", friendName, userLevel));

                // show remaining progress to level up in progressbar
                int moviesSingleDigit = moviesWatched % 10;
                ProgressBar progressBar = findViewById(R.id.progressBar);
                progressBar.setProgress(moviesSingleDigit);

                // show how many movies user needs to watch to level up
                int nextLevel = (userLevel) * 10;
                TextView moviesLeft = findViewById(R.id.moviesLeft);
                moviesLeft.setText(String.format("%d/%d", moviesWatched, nextLevel));

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

                        String timeStamp = databaseEntry.getString("timeStamp");
                        filmReview.setTimeStamp(timeStamp);

                        filmReviews.add(filmReview);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // show viewing history to user
                    ReviewAdapter adapter = new ReviewAdapter(getApplicationContext(), R.layout.filmreview, filmReviews);
                    listView.setAdapter(adapter);
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

    // listens if movie from view history is clicked
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // grabs movie that has been clicked
            FilmReview filmReview = (FilmReview) parent.getItemAtPosition(position);

            // direct user to movie review activity
            Intent intent = new Intent(FriendDetailActivity.this, FilmReviewActivity.class);
            intent.putExtra("filmReview", filmReview);
            intent.putExtra("fromFriends", "yes");
            startActivity(intent);
        }
    }

    // add delete icon to action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.remove, menu);
        return true;
    }

    // remove friend
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // check what the id of the friend is in database
        RequestQueue queue = Volley.newRequestQueue(this);

        // set right url to look up specific friend
        String url = String.format("https://ide50-danert.legacy.cs50.io:8080/%sfriendlist?friendName=%s", username, friendName);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                try {
                    JSONObject friendAccount = response.getJSONObject(0);
                    removalId = friendAccount.getInt("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // delete friend from database
                String deleteUrl = String.format("https://ide50-danert.legacy.cs50.io:8080/%sfriendlist/%d", username, removalId);
                HelperClass.removeEntry(getApplicationContext(), deleteUrl);
                Toast.makeText(getApplicationContext(), "Vriend verwijderd!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonArrayRequest);

        return true;
    }
}
