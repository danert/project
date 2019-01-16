package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class FilmReviewActivity extends AppCompatActivity {

    FilmReview filmReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Filmrecensie");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_review);

        // extract movie review
        Intent intent = getIntent();
        filmReview = (FilmReview) intent.getSerializableExtra("filmReview");

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

        // make sure user can't change rating (todo: is nog lelijk)
        ratingBar.setEnabled(false);

        // show review text
        TextView reviewText = findViewById(R.id.reviewText);
        String textReview = filmReview.getReviewText();
        reviewText.setText(textReview);
    }
}
