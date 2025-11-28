package com.sat.tmf.movietkt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sat.tmf.movietkt.entities.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {    
    @GetMapping("/")
    public String showHomePage(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("message", "Welcome to TMF Movie Ticket App with Spring Boot MVC");
        model.addAttribute("contentPage", "/WEB-INF/views/home.jsp");
        model.addAttribute("pageTitle", "Home");
        return "layout/layout";
    }
}
