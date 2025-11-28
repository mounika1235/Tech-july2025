package com.sat.tmf.movietkt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sat.tmf.movietkt.entities.Theater;
import com.sat.tmf.movietkt.service.TheaterService;

import java.util.List;

@Controller
@RequestMapping("/admin/theaters")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    // Display all theaters
    @GetMapping
    public String listTheaters(Model model) {
        List<Theater> theaters = theaterService.findAll();
        model.addAttribute("theaters", theaters);
        model.addAttribute("pageTitle", "Manage Theaters");
        model.addAttribute("contentPage", "/WEB-INF/views/admin/adminTheaters.jsp");
        return "layout/layout";
    }

    // Show add theater form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("theater", new Theater());
        model.addAttribute("contentPage", "/WEB-INF/views/admin/addTheater.jsp");
        model.addAttribute("pageTitle", "Add Theater");
        return "layout/layout";
    }

    // Handle create theater
    @PostMapping("/add")
    public String createTheater(@ModelAttribute Theater theater) {
        theaterService.createTheater(theater);
        return "redirect:/admin/theaters";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Theater theater = theaterService.findById(id);
        model.addAttribute("theater", theater);
        model.addAttribute("contentPage", "/WEB-INF/views/admin/addTheater.jsp");
        model.addAttribute("pageTitle", "Edit Theater");
        return "layout/layout";
    }

    // Handle update
    @PostMapping("/edit")
    public String updateTheater(@ModelAttribute Theater theater, Model model) {
        theaterService.createTheater(theater);
        List<Theater> theaterlist = theaterService.findAll();
        model.addAttribute("theaters",theaterlist);
        return "redirect:/admin/theaters";
    }

    // Delete theater
    @GetMapping("/delete/{id}")
    public String deleteTheater(@PathVariable Integer id) {
        theaterService.deleteTheater(id);
        return "redirect:/admin/theaters";
    }

    // Filter by city
    @GetMapping("/search")
    public String searchByCity(@RequestParam String city, Model model) {
        model.addAttribute("theaters", theaterService.findByCity(city));
        model.addAttribute("contentPage", "/WEB-INF/views/admin/adminTheaters.jsp");
        model.addAttribute("pageTitle", "Search Theaters");
        return "layout/layout";
    }
}
