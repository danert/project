package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // change action bar title (https://stackoverflow.com/questions/3438276/how-to-change-the-text-on-the-action-bar)
        getSupportActionBar().setTitle("Cinemaster");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void pageClicked(View v) {

        TextView pageName = (TextView) v;

        // check what page has been clicked
        Intent intent = new Intent(HomepageActivity.this, ProfileActivity.class);

        switch (pageName.getText().toString()) {
            case "Kijkgeschiedenis": intent = new Intent(HomepageActivity.this, ViewHistoryActivity.class);
                break;
            case "Watchlist": intent = new Intent(HomepageActivity.this, WatchlistActivity.class);
                break;
            case "Zoek een film": intent = new Intent(HomepageActivity.this, FilmSearchActivity.class);
                break;
        }

        // go to page
        startActivity(intent);
    }
}
