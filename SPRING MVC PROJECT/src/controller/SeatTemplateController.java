
package com.sat.tmf.movietkt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sat.tmf.movietkt.entities.Screen;
import com.sat.tmf.movietkt.entities.SeatTemplate;
import com.sat.tmf.movietkt.service.ScreenService;
import com.sat.tmf.movietkt.service.SeatTemplateService;

import java.util.List;

@Controller
@RequestMapping("/admin/templates")
public class SeatTemplateController {

    @Autowired
    private SeatTemplateService seatTemplateService;

    @Autowired
    private ScreenService screenService;

    // List templates for a screen
    @GetMapping("/screen/{screenId}")
    public String listTemplates(@PathVariable Integer screenId, Model model) {
        Screen screen = screenService.findById(screenId);
        List<SeatTemplate> templates = seatTemplateService.findByScreen(screen);
        model.addAttribute("screen", screen);
        model.addAttribute("templates", templates);
        model.addAttribute("contentPage", "/WEB-INF/views/admin/adminSeatTemplates.jsp");
        model.addAttribute("pageTitle", "Seat Templates for " + screen.getName());
        return "layout/layout";
    }

    // Show add form
    @GetMapping("/add/{screenId}")
    public String showAddForm(@PathVariable Integer screenId, Model model) {
        Screen screen = screenService.findById(screenId);
        SeatTemplate template = new SeatTemplate();
        template.setScreen(screen);
        model.addAttribute("template", template);
        model.addAttribute("screen", screen);
        model.addAttribute("contentPage", "/WEB-INF/views/admin/addSeatTemplate.jsp");
        model.addAttribute("pageTitle", "Create Seat Template");
        return "layout/layout";
    }

    // Handle create
    @PostMapping("/add")
    public String createTemplate(@ModelAttribute SeatTemplate template) {
        seatTemplateService.createTemplate(template);
        return "redirect:/admin/templates/screen/" + template.getScreen().getId();
    }

    // Edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        SeatTemplate template = seatTemplateService.findById(id);
        model.addAttribute("template", template);
        model.addAttribute("screen", template.getScreen());
        model.addAttribute("contentPage", "/WEB-INF/views/admin/addSeatTemplate.jsp");
        model.addAttribute("pageTitle", "Edit Seat Template");
        return "layout/layout";
    }

    // Handle update
    @PostMapping("/edit")
    public String updateTemplate(@ModelAttribute SeatTemplate template) {
        seatTemplateService.updateTemplate(template);
        return "redirect:/admin/templates/screen/" + template.getScreen().getId();
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String deleteTemplate(@PathVariable Integer id) {
        SeatTemplate template = seatTemplateService.findById(id);
        Integer screenId = template.getScreen().getId();
        seatTemplateService.deleteTemplate(id);
        return "redirect:/admin/templates/screen/" + screenId;
    }
}

