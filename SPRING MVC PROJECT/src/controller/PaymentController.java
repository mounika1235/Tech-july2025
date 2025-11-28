package com.sat.tmf.movietkt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sat.tmf.movietkt.entities.Booking;
import com.sat.tmf.movietkt.entities.Payment;
import com.sat.tmf.movietkt.entities.User;
import com.sat.tmf.movietkt.service.BookingService;
import com.sat.tmf.movietkt.service.PaymentService;
import com.sat.tmf.movietkt.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired private PaymentService paymentService;
    @Autowired private BookingService bookingService;
    @Autowired private UserService userService;

    // Initiate payment - show payment page
    @GetMapping("/start/{bookingId}")
    public String startPayment(@PathVariable Integer bookingId,  HttpSession session,  Model model) {
        // Check if user is logged in
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        Booking booking = bookingService.findById(bookingId);
        
        model.addAttribute("booking", booking);
        model.addAttribute("contentPage", "/WEB-INF/views/payment.jsp");
        model.addAttribute("pageTitle", "Make Payment");
        return "layout/layout";
    }

    // Process payment completion
    @PostMapping("/complete")
    public String completePayment(@RequestParam Integer bookingId, 
                                HttpSession session, 
                                Model model) {
        // Check if user is logged in
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        Booking booking = bookingService.findById(bookingId);
        
        // Initiate payment and handle success immediately (for demo)
        Payment payment = paymentService.initiatePayment(booking, user, "RAZORPAY");
        paymentService.handlePaymentSuccess(payment.getTxnId(), "RAZORPAY");
        
        // Get updated booking
        Booking confirmedBooking = bookingService.findById(bookingId);
        model.addAttribute("booking", confirmedBooking);
        model.addAttribute("contentPage", "/WEB-INF/views/bookingSuccess.jsp");
        model.addAttribute("pageTitle", "Booking Confirmed");
        return "layout/layout";
    }

    // Razorpay or Stripe webhook (callback)
    @PostMapping("/webhook")
    @ResponseBody
    public String handleWebhook(@RequestBody String payload) {
        // Example payload parsing
        if (payload.contains("payment_success")) {
            String txnId = extractTxnId(payload);
            paymentService.handlePaymentSuccess(txnId, "RAZORPAY");
        } else if (payload.contains("payment_failed")) {
            String txnId = extractTxnId(payload);
            paymentService.handlePaymentFailure(txnId, "RAZORPAY");
        }
        return "OK";
    }

    private String extractTxnId(String payload) {
        // In real use, parse JSON from Razorpay/Stripe webhook payload
        int start = payload.indexOf("txn_");
        return start != -1 ? payload.substring(start, start + 36) : "unknown";
    }
}
