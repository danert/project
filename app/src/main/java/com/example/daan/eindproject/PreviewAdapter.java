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

public class PreviewAdapter extends ArrayAdapter<MovieInfo> {

    ArrayList<MovieInfo> suggestions;

    // constructor
    public PreviewAdapter(Context context, int resource, ArrayList<MovieInfo> filmSuggestions) {
        super(context, resource, filmSuggestions);
        suggestions = filmSuggestions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.filmpreview, parent, false);
        }

        // grab suggestion
        MovieInfo suggestion = suggestions.get(position);

        // set name and release year of movie
        TextView titleView = convertView.findViewById(R.id.titleView);
        String suggestionText = suggestion.getReleaseTitle();
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
