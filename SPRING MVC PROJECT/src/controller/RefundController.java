package com.sat.tmf.movietkt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sat.tmf.movietkt.entities.Refund;
import com.sat.tmf.movietkt.entities.User;
import com.sat.tmf.movietkt.service.RefundService;
import com.sat.tmf.movietkt.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/refunds")
public class RefundController {

    @Autowired
    private RefundService refundService;

    @Autowired
    private UserService userService;

    // User view - using HttpSession instead of Principal
    @GetMapping
    public String userRefunds(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        
        // Check if user is logged in
        if (currentUser == null) {
            return "redirect:/user/login";
        }
        
        List<Refund> refunds = refundService.getRefundsForUser(currentUser);
        model.addAttribute("refunds", refunds);
        model.addAttribute("contentPage", "/WEB-INF/views/userRefunds.jsp");
        model.addAttribute("pageTitle", "My Refunds");
        return "layout/layout";
    }

    // Refund status details for user
    @GetMapping("/status/{refundId}")
    public String refundStatus(@PathVariable Integer refundId, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        
        // Check if user is logged in
        if (currentUser == null) {
            return "redirect:/user/login";
        }
        
        List<Refund> userRefunds = refundService.getRefundsForUser(currentUser);
        Refund refund = userRefunds.stream()
                .filter(r -> r.getId().equals(refundId))
                .findFirst()
                .orElse(null);
        
        if (refund == null) {
            return "redirect:/refunds";
        }
        
        model.addAttribute("refund", refund);
        model.addAttribute("contentPage", "/WEB-INF/views/refundStatus.jsp");
        model.addAttribute("pageTitle", "Refund Status");
        return "layout/layout";
    }

    // Admin view
    @GetMapping("/admin")
    public String adminRefunds(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        
        // Check if user is logged in and is admin
        if (currentUser == null) {
            return "redirect:/user/login";
        }
        
        if (!"ADMIN".equalsIgnoreCase(currentUser.getRole())) {
            return "redirect:/";
        }
        
        List<Refund> refunds = refundService.getAllRefunds();
        model.addAttribute("refunds", refunds);
        model.addAttribute("contentPage", "/WEB-INF/views/admin/adminRefunds.jsp");
        model.addAttribute("pageTitle", "Refund Management");
        return "layout/layout";
    }
}
