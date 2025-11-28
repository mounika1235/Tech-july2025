package com.sat.tmf.movietkt.service;

import java.util.List;

import com.sat.tmf.movietkt.entities.Movie;

public interface MovieService {
    Movie addMovie(Movie movie);
    Movie updateMovie(Movie movie);
    void deleteMovie(Integer id);
    Movie findById(Integer id);
    List<Movie> findAllMovies();
    List<Movie> searchMovies(String keyword);
	void createMovie(Movie movie);
	List<Movie> findMoviesForDisplay();
}
