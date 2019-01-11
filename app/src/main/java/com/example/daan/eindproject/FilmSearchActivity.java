package com.example.daan.eindproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
                ArrayList<FilmSuggestion> filmSuggestions = new ArrayList<FilmSuggestion>();

                JSONArray suggestions = new JSONArray();
                try {
                    suggestions = response.getJSONArray("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // parse array for movie titles
                for (int i = 0; i < suggestions.length(); i++) {
                    try {

                        // create new filmsuggestion object
                        FilmSuggestion filmSuggestion = new FilmSuggestion();

                        // grab whole suggestion
                        JSONObject suggestion = suggestions.getJSONObject(i);

                        // grab movie title from suggestion
                        String movieTitle = suggestion.getString("title");
                        filmSuggestion.setMovieTitle(movieTitle);

                        // grab poster url from suggestion
                        String posterUrl = suggestion.getString("poster_path");
                        filmSuggestion.setPosterUrl(posterUrl);

                        // grab id from suggestion
                        String movieId = suggestion.getString("id");
                        filmSuggestion.setMovieId(movieId);

                        // grab release year from suggestion
                        String releaseDate = suggestion.getString("release_date");
                        String releaseYear = releaseDate.substring(0, 3);
                        filmSuggestion.setReleaseYear(releaseYear);

                        // add movie title to list
                        filmSuggestions.add(filmSuggestion);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // show movie titles to user
                ListView searchResults = findViewById(R.id.searchResults);

                // instantiate adapter
                SuggestionAdapter adapter = new SuggestionAdapter(getApplicationContext(), R.layout.filmsuggestion, filmSuggestions);
                searchResults.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonObjectRequest);


    }



}
