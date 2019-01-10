package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void pageClicked(View v) {

        TextView pageName = (TextView) v;

        // check what page has been clicked
        Intent intent = new Intent(HomepageActivity.this, ProfileActivity.class);

        switch (pageName.getText().toString()) {
            case "Kijkgeschiedenis": intent = new Intent(HomepageActivity.this, ViewHistoryActivity.class);
            case "Watchlist": intent = new Intent(HomepageActivity.this, WatchlistActivity.class);
        }

        // go to page
        startActivity(intent);
    }
}
