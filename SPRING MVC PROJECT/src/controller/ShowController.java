package com.sat.tmf.movietkt.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sat.tmf.movietkt.entities.Movie;
import com.sat.tmf.movietkt.entities.Screen;
import com.sat.tmf.movietkt.entities.SeatTemplate;
import com.sat.tmf.movietkt.entities.Show;
import com.sat.tmf.movietkt.service.MovieService;
import com.sat.tmf.movietkt.service.ScreenService;
import com.sat.tmf.movietkt.service.SeatTemplateService;
import com.sat.tmf.movietkt.service.ShowService;

@Controller
@RequestMapping("/admin/shows")
public class ShowController {

    @Autowired private ShowService showService;
    @Autowired private ScreenService screenService;
    @Autowired private SeatTemplateService seatTemplateService;
    @Autowired private MovieService movieService;

    // List all shows for a screen
    @GetMapping("/screen/{screenId}")
    public String listShows(@PathVariable Integer screenId, Model model) {
        Screen screen = screenService.findById(screenId);
        List<Show> shows = showService.findByScreen(screen);
        model.addAttribute("screen", screen);
        model.addAttribute("shows", shows);
        model.addAttribute("contentPage", "/WEB-INF/views/admin/adminShows.jsp");
        model.addAttribute("pageTitle", "Shows for " + screen.getName());
        return "layout/layout";
    }

    // Show form for adding a new show
    @GetMapping("/add/{screenId}")
    public String showAddForm(@PathVariable Integer screenId, Model model) {
        Screen screen = screenService.findById(screenId);
        List<Movie> movies = movieService.findAllMovies();
        List<SeatTemplate> templates = seatTemplateService.findByScreen(screen);

        Show show = new Show();
        show.setScreen(screen);

        model.addAttribute("screen", screen);
        model.addAttribute("show", show);
        model.addAttribute("movies", movies);
        model.addAttribute("templates", templates);
        model.addAttribute("contentPage", "/WEB-INF/views/admin/addShow.jsp");
        model.addAttribute("pageTitle", "Add New Show");
        return "layout/layout";
    }

    // Handle creation
    @PostMapping("/add")
    public String createShow(@ModelAttribute Show show,
                             @RequestParam("movie.id") Integer movieId,
                             @RequestParam("screen.id") Integer screenId,
                             @RequestParam("seatTemplate.id") Integer templateId,
                             @RequestParam("showTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime showTime) {

        show.setMovie(movieService.findById(movieId));
        show.setScreen(screenService.findById(screenId));
        show.setSeatTemplate(seatTemplateService.findById(templateId));
        show.setShowTime(showTime);
        show.setTotalSeats(show.getSeatTemplate().getRows_no() * show.getSeatTemplate().getCols());

        showService.createShow(show);
        return "redirect:/admin/shows/screen/" + screenId;
    }

    // Delete show
    @GetMapping("/delete/{id}")
    public String deleteShow(@PathVariable Integer id) {
        Show show = showService.findById(id);
        Integer screenId = show.getScreen().getId();
        showService.deleteShow(id);
        return "redirect:/admin/shows/screen/" + screenId;
    }
}
