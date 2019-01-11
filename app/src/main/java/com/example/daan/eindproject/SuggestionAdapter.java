package com.example.daan.eindproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SuggestionAdapter extends ArrayAdapter<FilmSuggestion> {

    ArrayList<FilmSuggestion> suggestions;

    public SuggestionAdapter(Context context, int resource, ArrayList<FilmSuggestion> filmSuggestions) {
        super(context, resource, filmSuggestions);
        suggestions = filmSuggestions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.filmsuggestion, parent, false);
        }

        // grab suggestion
        FilmSuggestion suggestion = suggestions.get(position);

        // set name and release year of movie
        TextView titleView = convertView.findViewById(R.id.titleView);
        String movieTitle = suggestion.getMovieTitle();
        String releaseYear = suggestion.getReleaseYear();
        String suggestionText = String.format("%s (%s)", movieTitle, releaseYear);
        titleView.setText(suggestionText);

        // set movie poster
        String posterUrl = suggestion.getPosterUrl();
        String url = String.format("http://image.tmdb.org/t/p/w185/%s", posterUrl);
        ImageView posterView = convertView.findViewById(R.id.posterView);

        // https://stackoverflow.com/questions/43971819/android-how-to-set-an-image-to-an-imageview-from-a-url-programatically
        Picasso.with(getContext()).load(url).fit().into(posterView);

        return convertView;
    }
}
