
package com.sat.tmf.movietkt.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sat.tmf.movietkt.entities.Movie;
import com.sat.tmf.movietkt.entities.MovieReview;
import com.sat.tmf.movietkt.entities.User;
import com.sat.tmf.movietkt.service.MovieReviewService;
import com.sat.tmf.movietkt.service.MovieService;
import com.sat.tmf.movietkt.service.UserService;

@Controller
@RequestMapping("admin/movies")
public class MovieReviewController {

    @Autowired private MovieService movieService;
    @Autowired private MovieReviewService reviewService;
    @Autowired private UserService userService;

    @GetMapping("/details/{id}")
    public String movieDetails(@PathVariable Integer id, Model model) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return "redirect:/admin/movies/Movies";
        }
        List<MovieReview> reviews = reviewService.findByMovie(movie);
        Double avgRating = reviewService.getAverageRating(movie);

        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviews);
        model.addAttribute("avgRating", avgRating);
        model.addAttribute("contentPage", "/WEB-INF/views/movieDetails.jsp");
        model.addAttribute("pageTitle", movie.getTitle());
        return "layout/layout";
    }

    @PostMapping("/{id}/review")
    @ResponseBody
    public String submitReview(@PathVariable Integer id,
                               @RequestParam int rating,
                               @RequestParam String comment,
                               Principal principal) {
        if (principal == null) {
            return "ERROR: Login required";
        }

        Movie movie = movieService.findById(id);
        User user = userService.findByUsername(principal.getName());
        reviewService.addReview(movie, user, rating, comment);
        return "OK";
    }
}
