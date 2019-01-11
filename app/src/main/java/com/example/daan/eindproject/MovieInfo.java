package com.example.daan.eindproject;

import java.io.Serializable;

public class MovieInfo implements Serializable {

    String movieTitle, posterUrl, releaseYear, movieId, moviePlot;

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setMoviePlot(String moviePlot) {
        this.moviePlot = moviePlot;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getMoviePlot() {
        return moviePlot;
    }
}
