package com.example.daan.eindproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

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
