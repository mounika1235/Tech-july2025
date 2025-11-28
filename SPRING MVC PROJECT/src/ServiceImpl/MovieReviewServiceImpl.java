package com.sat.tmf.movietkt.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sat.tmf.movietkt.dao.MovieReviewDao;
import com.sat.tmf.movietkt.entities.Movie;
import com.sat.tmf.movietkt.entities.MovieReview;
import com.sat.tmf.movietkt.entities.User;
import com.sat.tmf.movietkt.service.MovieReviewService;

@Service
@Transactional
public class MovieReviewServiceImpl implements MovieReviewService {

    @Autowired
    private MovieReviewDao reviewDao;

    @Override
    public MovieReview addReview(Movie movie, User user, int rating, String comment) {
        MovieReview review = new MovieReview();
        review.setMovie(movie);
        review.setUser(user);
        review.setRating(rating);
        review.setComment(comment);
        review.setCreatedAt(LocalDateTime.now());
        reviewDao.save(review);
        return review;
    }

    @Override
    public List<MovieReview> findByMovie(Movie movie) {
        return reviewDao.findByMovie(movie);
    }

    @Override
    public Double getAverageRating(Movie movie) {
        return reviewDao.findAverageRating(movie);
    }
}
