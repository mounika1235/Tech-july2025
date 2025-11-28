
package com.sat.tmf.movietkt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sat.tmf.movietkt.entities.Booking;
import com.sat.tmf.movietkt.entities.Show;
import com.sat.tmf.movietkt.entities.User;
import com.sat.tmf.movietkt.service.BookingService;
import com.sat.tmf.movietkt.service.ShowService;
import com.sat.tmf.movietkt.service.TemplateSeatService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired private BookingService bookingService;
    @Autowired private ShowService showService;
    @Autowired private TemplateSeatService seatService;

    // View seat map for a show
    @GetMapping("/select/{showId}") // Add HttpSession parameter
    public String selectSeats(@PathVariable Integer showId,  HttpSession session,  Model model) {
        // Check if user is logged in
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/user/login"; // Redirect to your existing login page
        }
        
        Show show = showService.findById(showId);
        model.addAttribute("show", show);
        model.addAttribute("templateSeats", seatService.findByTemplateId(show.getSeatTemplate().getId()));
        model.addAttribute("contentPage", "/WEB-INF/views/selectSeats.jsp");
        model.addAttribute("pageTitle", "Select Seats for " + show.getMovie().getTitle());
        return "layout/layout";
    }

    // Hold seats
    @PostMapping("/hold")
    public String holdSeats(@RequestParam Integer showId,
                           @RequestParam List<Integer> seatIds,
                           HttpSession session, Model model) {
        
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        System.out.println("Hold seats called - Show ID: " + showId + ", Seat IDs: " + seatIds);
        
        Show show = showService.findById(showId);
        Booking booking = bookingService.holdSeats(user, show, seatIds);
        
        System.out.println("Booking created - ID: " + booking.getId() + ", Status: " + booking.getStatus());
        
        model.addAttribute("booking", booking);
        model.addAttribute("contentPage", "/WEB-INF/views/confirmBooking.jsp");
        model.addAttribute("pageTitle", "Confirm Booking");
        return "layout/layout";
    }

    // Confirm booking
//    @PostMapping("/confirm")
//    public String confirmBooking(@RequestParam Integer bookingId, Model model) {
//        Booking booking = bookingService.confirmBooking(bookingId);
//        model.addAttribute("booking", booking);
//        model.addAttribute("contentPage", "/WEB-INF/views/bookingSuccess.jsp");
//        model.addAttribute("pageTitle", "Booking Confirmed");
//        return "layout/layout";
//    }
		 @PostMapping("/confirm")
		 public String confirmBooking(@RequestParam Integer bookingId, HttpSession session, Model model) {
		     User user = (User) session.getAttribute("currentUser");
		     if (user == null) {
		         return "redirect:/user/login";
		     }
		     Booking booking = bookingService.findById(bookingId);
		     return "redirect:/payment/start/" + bookingId;
		 }
		 
		@GetMapping("/history")
		public String viewHistory(HttpSession session, Model model) {
		  User user = (User) session.getAttribute("currentUser");
		  if (user == null) {
		      return "redirect:/user/login";
		  }
		  
		  List<Booking> bookings = bookingService.findByUser(user);
		  System.out.println("Found " + bookings.size() + " bookings for user: " + user.getUsername());
		  
		  model.addAttribute("bookings", bookings);
		  model.addAttribute("contentPage", "/WEB-INF/views/bookingHistory.jsp");
		  model.addAttribute("pageTitle", "My Bookings");
		  return "layout/layout";
		}
	}
