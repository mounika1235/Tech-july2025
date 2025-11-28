package com.sat.tmf.movietkt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sat.tmf.movietkt.entities.SeatTemplate;
import com.sat.tmf.movietkt.entities.TemplateSeat;
import com.sat.tmf.movietkt.service.SeatTemplateService;
import com.sat.tmf.movietkt.service.TemplateSeatService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/seats")
public class TemplateSeatController {

    @Autowired
    private SeatTemplateService seatTemplateService;

    @Autowired
    private TemplateSeatService templateSeatService;

    // View & edit seat layout
    @GetMapping("/template/{templateId}")
    public String editSeatLayout(@PathVariable Integer templateId, Model model) {
        SeatTemplate template = seatTemplateService.findById(templateId);
     // Check if template exists
        if (template == null) {
            // Handle template not found
            return "redirect:/admin/templates";
        }
        List<TemplateSeat> seats = templateSeatService.findByTemplateId(templateId);
        model.addAttribute("template", template);
        model.addAttribute("seats", seats);
        model.addAttribute("contentPage", "/WEB-INF/views/admin/adminManageSeats.jsp");
        model.addAttribute("pageTitle", "Manage Seats for " + template.getName());
        return "layout/layout";
    }

    // Save seat layout (AJAX)
    @PostMapping("/saveLayout/{templateId}")
    @ResponseBody
    public String saveSeatLayout(@PathVariable Integer templateId,
                                 @RequestBody List<TemplateSeat> seats) {
        templateSeatService.deleteSeatsByTemplate(templateId);
        for (TemplateSeat s : seats) {
            s.setSeatTemplate(seatTemplateService.findById(templateId));
            templateSeatService.saveSeat(s);
        }
        return "SUCCESS";
    }
}
