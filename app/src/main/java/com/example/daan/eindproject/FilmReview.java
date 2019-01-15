package com.example.daan.eindproject;

import java.io.Serializable;

public class FilmReview implements Serializable {

    String movieId, releaseTitle, reviewText, posterUrl;
    float starRating;


    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setReleaseTitle(String releaseTitle) {
        this.releaseTitle = releaseTitle;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setStarRating(float starRating) {
        this.starRating = starRating;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getReleaseTitle() {
        return releaseTitle;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public float getStarRating() {
        return starRating;
    }
}
