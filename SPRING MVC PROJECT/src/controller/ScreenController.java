
package com.sat.tmf.movietkt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sat.tmf.movietkt.entities.Screen;
import com.sat.tmf.movietkt.entities.Theater;
import com.sat.tmf.movietkt.service.ScreenService;
import com.sat.tmf.movietkt.service.TheaterService;

import java.util.List;

@Controller
@RequestMapping("/admin/screens")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    @Autowired
    private TheaterService theaterService;

    // List screens for a theater
    @GetMapping("/theater/{theaterId}")
    public String listScreens(@PathVariable Integer theaterId, Model model) {
        Theater theater = theaterService.findById(theaterId);
        List<Screen> screens = screenService.findByTheater(theater);
        model.addAttribute("theater", theater);
        model.addAttribute("screens", screens);
        model.addAttribute("contentPage", "/WEB-INF/views/admin/adminScreens.jsp");
        model.addAttribute("pageTitle", "Manage Screens for " + theater.getName());
        return "layout/layout";
    }

    // Show add screen form
    @GetMapping("/add/{theaterId}")
    public String showAddForm(@PathVariable Integer theaterId, Model model) {
        Theater theater = theaterService.findById(theaterId);
        Screen screen = new Screen();
        screen.setTheater(theater);
        model.addAttribute("screen", screen);
        model.addAttribute("theater", theater);
        model.addAttribute("contentPage", "/WEB-INF/views/admin/addScreen.jsp");
        model.addAttribute("pageTitle", "Add Screen");
        return "layout/layout";
    }

    // Handle create
    @PostMapping("/add")
    public String createScreen(@ModelAttribute Screen screen) {
        screenService.createScreen(screen);
        return "redirect:/admin/screens/theater/" + screen.getTheater().getId();
    }

    // Edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Screen screen = screenService.findById(id);
        model.addAttribute("screen", screen);
        model.addAttribute("theater", screen.getTheater());
        model.addAttribute("contentPage", "/WEB-INF/views/admin/addScreen.jsp");
        model.addAttribute("pageTitle", "Edit Screen");
        return "layout/layout";
    }

    @PostMapping("/edit")
    public String updateScreen(@ModelAttribute Screen screen) {
        screenService.updateScreen(screen); 
        return "redirect:/admin/screens/theater/" + screen.getTheater().getId(); 
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String deleteScreen(@PathVariable Integer id) {
        Screen screen = screenService.findById(id);
        Integer theaterId = screen.getTheater().getId();
        screenService.deleteScreen(id);
        return "redirect:/admin/screens/theater/" + theaterId;
    }
}

