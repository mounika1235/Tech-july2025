
package com.sat.tmf.movietkt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sat.tmf.movietkt.entities.User;
import com.sat.tmf.movietkt.service.MovieService;
import com.sat.tmf.movietkt.service.ScreenService;
import com.sat.tmf.movietkt.service.ShowService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
    
    @Autowired
    private MovieService movieService;
    
    @Autowired
    private ScreenService screenService;
    
    @Autowired
    private ShowService showService;
    
    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/user/login";
        }
        
        // Check if user is admin
        if (!"ADMIN".equalsIgnoreCase(currentUser.getRole())) {
            return "redirect:/";
        }
        
        // Add all required data for the dashboard
        model.addAttribute("movies", movieService.findAllMovies());
        model.addAttribute("screens", screenService.findAll());
        model.addAttribute("shows", showService.findAll());
        
        model.addAttribute("contentPage", "/WEB-INF/views/adminDashboard.jsp");
        model.addAttribute("pageTitle", "Admin Dashboard");
        return "layout/layout";
    }
    
    // Add this method for deleting movies
    @PostMapping("/admin/deleteMovie")
    public String deleteMovie(@RequestParam("movieId") Integer movieId, 
                            HttpSession session,
                            Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/user/login";
        }
        
        // Check if user is admin
        if (!"ADMIN".equalsIgnoreCase(currentUser.getRole())) {
            return "redirect:/";
        }
        
        try {
            movieService.deleteMovie(movieId);
            model.addAttribute("successMessage", "Movie deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error deleting movie: " + e.getMessage());
        }
        
        // Return to dashboard with updated movie list
        model.addAttribute("movies", movieService.findAllMovies());
        model.addAttribute("screens", screenService.findAll());
        model.addAttribute("shows", showService.findAll());
        model.addAttribute("contentPage", "/WEB-INF/views/adminDashboard.jsp");
        model.addAttribute("pageTitle", "Admin Dashboard");
        return "layout/layout";
    }
}
