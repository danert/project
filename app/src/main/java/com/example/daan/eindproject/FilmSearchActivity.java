package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FilmSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Zoeken");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_search);
    }


    // if input is entered in search bar, show suggestions
    public void inputEntered(View v) {

        // grab input
        EditText searchBar = findViewById(R.id.filmInput);
        String input = searchBar.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = String.format("https://api.themoviedb.org/3/search/movie?api_key=41bdf5a584e9b0eb718c12c81ed7b264&query=%s", input);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {

            // when suggestions were received
            @Override
            public void onResponse(JSONObject response) {

                // retrieve list of movie titles from suggestions
                ArrayList<MovieInfo> filmSuggestions = new ArrayList<MovieInfo>();

                JSONArray suggestions = new JSONArray();
                try {
                    suggestions = response.getJSONArray("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // parse array for movie titles
                for (int i = 0; i < suggestions.length(); i++) {
                    try {

                        // create new filmpreview object
                        MovieInfo movieInfo = new MovieInfo();

                        // grab whole suggestion
                        JSONObject suggestion = suggestions.getJSONObject(i);

                        // grab movie title and release year from suggestion
                        String movieTitle = suggestion.getString("title");

                        // try catch to prevent error, but might cause no title to appear?
                        try {
                            String releaseDate = suggestion.getString("release_date");
                            String releaseYear = releaseDate.substring(0, 4);
                            String releaseTitle = String.format("%s (%s)", movieTitle, releaseYear);
                            movieInfo.setReleaseTitle(releaseTitle);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // grab poster url from suggestion
                        String posterUrl = suggestion.getString("poster_path");
                        movieInfo.setPosterUrl(posterUrl);

                        // grab id from suggestion
                        String movieId = suggestion.getString("id");
                        movieInfo.setMovieId(movieId);

                        // grab plot from suggestion
                        String moviePlot = suggestion.getString("overview");
                        movieInfo.setMoviePlot(moviePlot);

                        // add movie info to list
                        filmSuggestions.add(movieInfo);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // show movie titles to user
                ListView searchResults = findViewById(R.id.searchResults);

                // instantiate adapter
                PreviewAdapter adapter = new PreviewAdapter(getApplicationContext(), R.layout.filmpreview, filmSuggestions);
                searchResults.setAdapter(adapter);

                // connect listview to listener
                searchResults.setOnItemClickListener(new ListItemClickListener());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonObjectRequest);
    }

    // listens if movie from suggestions is clicked (NOT YET CONNECTED TO THE LISTVIEW)
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // grabs film suggestion that has been clicked
            MovieInfo movieInfo = (MovieInfo) parent.getItemAtPosition(position);

            // direct user to movie info activity
            Intent intent = new Intent(FilmSearchActivity.this, MovieInfoActivity.class);
            intent.putExtra("movieInfo", movieInfo);
            startActivity(intent);
        }
    }
}
