package com.sat.tmf.movietkt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sat.tmf.movietkt.entities.Movie;
import com.sat.tmf.movietkt.entities.Show;
import com.sat.tmf.movietkt.service.MovieService;
import com.sat.tmf.movietkt.service.ShowService;

@Controller
@RequestMapping("/admin/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    
    @Autowired
    private ShowService showService;

    // =================== ADMIN MOVIE MANAGEMENT ===================
    @GetMapping
    public String listMovies(Model model, @RequestParam(required = false) String search) {
        List<Movie> movies;
        if (search != null && !search.isEmpty()) {
            movies = movieService.searchMovies(search);
        } else {
            movies = movieService.findAllMovies();
        }
        model.addAttribute("movies", movies);
        model.addAttribute("search", search);
        model.addAttribute("contentPage", "/WEB-INF/views/admin/adminMovies.jsp");
        model.addAttribute("pageTitle", "Manage Movies");
        return "layout/layout";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("contentPage", "/WEB-INF/views/admin/addMovie.jsp");
        model.addAttribute("pageTitle", "Add Movie");
        return "layout/layout";
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/admin/movies";
    }

    @GetMapping("/edit/{id}")
    public String editMovie(@PathVariable Integer id, Model model) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return "redirect:/admin/movies";
        }
        model.addAttribute("movie", movie);
        model.addAttribute("contentPage", "/WEB-INF/views/admin/addMovie.jsp");
        model.addAttribute("pageTitle", "Edit Movie");
        return "layout/layout";
    }

    @PostMapping("/edit/{id}")
    public String updateMovie(@PathVariable Integer id, @ModelAttribute Movie movie) {
        movie.setId(id);
        movieService.updateMovie(movie);
        return "redirect:/admin/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return "redirect:/admin/movies";
    }
    
    // =================== USER MOVIE BROWSING ===================
//    @GetMapping("/Movies")
//    public String listMoviesForUser(@RequestParam(required = false) String search,
//                                    Model model) {
//        List<Movie> movies;
//
//        if (search != null && !search.isEmpty()) {
//            movies = movieService.searchMovies(search);
//        } else {
//            movies = movieService.findAllMovies();
//        }
//
//        model.addAttribute("movies", movies);
//        model.addAttribute("search", search);
//        model.addAttribute("contentPage", "/WEB-INF/views/movies.jsp");
//        model.addAttribute("pageTitle", "Now Showing");
//        return "layout/layout";
//    }
    
    @GetMapping("/Movies")
    public String listMoviesForUser(@RequestParam(required = false) String search,
                                    Model model) {
        List<Movie> movies;

        if (search != null && !search.isEmpty()) {
            movies = movieService.searchMovies(search);
        } else {
            movies = movieService.findMoviesForDisplay(); // Use the new method
        }

        model.addAttribute("movies", movies);
        model.addAttribute("search", search);
        model.addAttribute("contentPage", "/WEB-INF/views/movies.jsp");
        model.addAttribute("pageTitle", "Now Showing");
        return "layout/layout";
    }
    @GetMapping("/{id}/shows")
    public String listShowsForMovie(@PathVariable Integer id, Model model) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return "redirect:/admin/movies/Movies";
        }
        List<Show> shows = showService.findUpcomingShows(movie);
        model.addAttribute("movie", movie);
        model.addAttribute("shows", shows);
        model.addAttribute("contentPage", "/WEB-INF/views/movieShows.jsp");
        model.addAttribute("pageTitle", movie.getTitle() + " - Showtimes");
        return "layout/layout";
    }
}
