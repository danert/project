package com.example.daan.eindproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReviewAdapter extends ArrayAdapter<FilmReview> {

    ArrayList<FilmReview> filmReviews;

    // constructor
    public ReviewAdapter(Context context, int resource, ArrayList<FilmReview> filmReviews) {
        super(context, resource, filmReviews);
        this.filmReviews = filmReviews;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.filmreview, parent, false);
        }

        // grab filmreview
        FilmReview filmReview = filmReviews.get(position);

        // set name and release year of movie
        TextView titleView = convertView.findViewById(R.id.titleView);
        String releaseTitle = filmReview.getReleaseTitle();
        Log.i("releaseTitle", releaseTitle);
        titleView.setText(releaseTitle);

        // set movie poster
        String posterUrl = filmReview.getPosterUrl();
        String url = String.format("http://image.tmdb.org/t/p/w185/%s", posterUrl);
        ImageView posterView = convertView.findViewById(R.id.posterView);
        Picasso.with(getContext()).load(url).fit().into(posterView);

        // https://stackoverflow.com/questions/43971819/android-how-to-set-an-image-to-an-imageview-from-a-url-programatically
        Picasso.with(getContext()).load(url).fit().into(posterView);

        // set star rating
        float starRating = filmReview.getStarRating();
        RatingBar ratingBar = convertView.findViewById(R.id.ratingBar);
        ratingBar.setRating(starRating);

        return convertView;
    }
}

