package com.sat.tmf.movietkt.service;

import java.util.List;

import com.sat.tmf.movietkt.entities.Movie;
import com.sat.tmf.movietkt.entities.MovieReview;
import com.sat.tmf.movietkt.entities.User;

public interface MovieReviewService {
    MovieReview addReview(Movie movie, User user, int rating, String comment);
    List<MovieReview> findByMovie(Movie movie);
    Double getAverageRating(Movie movie);
}
