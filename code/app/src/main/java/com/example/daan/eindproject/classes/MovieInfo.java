package com.example.daan.eindproject.classes;

import java.io.Serializable;

public class MovieInfo implements Serializable {

    String posterUrl, releaseTitle, movieId, moviePlot;

    public void setReleaseTitle(String releaseTitle) {
        this.releaseTitle = releaseTitle;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setMoviePlot(String moviePlot) {
        this.moviePlot = moviePlot;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getMoviePlot() {
        return moviePlot;
    }

    public String getReleaseTitle() {
        return releaseTitle;
    }
}
