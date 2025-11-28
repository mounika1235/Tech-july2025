package com.sat.tmf.movietkt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sat.tmf.movietkt.dao.MovieDao;
import com.sat.tmf.movietkt.entities.Movie;
import com.sat.tmf.movietkt.service.MovieService;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Override
    public Movie addMovie(Movie movie) {
        movieDao.save(movie);
        return movie;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        movieDao.saveOrUpdate(movie);
        return movie;
    }

    @Override
    public void deleteMovie(Integer id) {
        Movie movie = movieDao.findById(id);
        if (movie != null) {
            movieDao.delete(movie);
        }
    }

    @Override
    public Movie findById(Integer id) {
        return movieDao.findById(id);
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieDao.findAll();
    }

    @Override
    public List<Movie> searchMovies(String keyword) {
        return movieDao.searchMovies(keyword);
    }

	@Override
	public void createMovie(Movie movie) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Movie> findMoviesForDisplay() {
	    List<Movie> allMovies = movieDao.findAll();
	    
	    // If we have less than 10 movies, return all
	    if (allMovies.size() <= 10) {
	        return allMovies;
	    }
	    
	    // Create a list with at least one movie from each genre
	    Map<String, Movie> genreMovies = new HashMap<>();
	    List<Movie> result = new ArrayList<>();
	    
	    // First pass: get one movie from each genre
	    for (Movie movie : allMovies) {
	        if (movie.getGenre() != null) {
	            String[] genres = movie.getGenre().split(",");
	            for (String genre : genres) {
	                String cleanGenre = genre.trim();
	                if (!genreMovies.containsKey(cleanGenre)) {
	                    genreMovies.put(cleanGenre, movie);
	                    result.add(movie);
	                    break; // Only use this movie for one genre
	                }
	            }
	        }
	    }
	    
	    // Second pass: fill remaining slots with any movies
	    for (Movie movie : allMovies) {
	        if (result.size() >= 10) break;
	        if (!result.contains(movie)) {
	            result.add(movie);
	        }
	    }
	    
	    return result;
	}
}
