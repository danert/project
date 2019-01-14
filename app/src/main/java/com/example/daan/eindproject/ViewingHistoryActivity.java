package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewingHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Kijkgeschiedenis");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        // example list of movies for prototype, delete later
        ArrayList<String> exampleMovies = new ArrayList<String>();
        exampleMovies.add("Requiem for a Dream");
        exampleMovies.add("Hereditary");
        exampleMovies.add("The Tree of Life");
        exampleMovies.add("Grave of the Fireflies");
        exampleMovies.add("Trance");

        ListView watchlistView = findViewById(R.id.viewhistoryList);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.textview, R.id.textView6, exampleMovies);

        watchlistView.setAdapter(arrayAdapter);
        // test for prototype ends here

        // set listener for listview
        watchlistView.setOnItemClickListener(new ListItemClickListener());
    }

    // listens if movie from view history is clicked
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // grabs movie title that has been clicked
            String movieTitle = (String) parent.getItemAtPosition(position);

            // direct user to movie movie info activity
            Intent intent = new Intent(ViewingHistoryActivity.this, FilmReviewActivity.class);
            intent.putExtra("movieTitle", movieTitle);
            startActivity(intent);
        }
    }


}
