
package com.sat.tmf.movietkt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sat.tmf.movietkt.entities.User;
import com.sat.tmf.movietkt.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // =================== REGISTER ===================
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("contentPage", "/WEB-INF/views/register.jsp");
        model.addAttribute("pageTitle", "Register");
        return "layout/layout";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.register(user);
            model.addAttribute("message", "Registration successful! You can now log in.");
            model.addAttribute("user", new User()); 
            model.addAttribute("contentPage", "/WEB-INF/views/login.jsp");
            model.addAttribute("pageTitle", "Login");
            return "layout/layout"; 
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("contentPage", "/WEB-INF/views/register.jsp");
            model.addAttribute("pageTitle", "Register");
            return "layout/layout";
        }
    }

	 // =================== LOGIN ===================
	    @GetMapping("/login")
	    public String showLoginPage(Model model) {
	        model.addAttribute("user", new User());
	        model.addAttribute("contentPage", "/WEB-INF/views/login.jsp");
	        model.addAttribute("pageTitle", "login");
	        return "layout/layout";
	    }
    
	    
//	    @PostMapping("/login")
//	    public String loginUser(@RequestParam String username, 
//	                           @RequestParam String password,
//	                           HttpSession session,
//	                           Model model) {
//	        try {
//	            User user = userService.authenticate(username, password);
//	            if (user != null) {
//	                session.setAttribute("currentUser", user);
//	                return "redirect:/";
//	            } else {
//	                model.addAttribute("error", "Invalid username or password");
//	                model.addAttribute("user", new User());
//	                model.addAttribute("contentPage", "/WEB-INF/views/login.jsp");
//	                model.addAttribute("pageTitle", "Login");
//	                return "layout/layout";
//	            }
//	        } catch (Exception e) {
//	            model.addAttribute("error", "Login failed: " + e.getMessage());
//	            model.addAttribute("user", new User());
//	            model.addAttribute("contentPage", "/WEB-INF/views/login.jsp");
//	            model.addAttribute("pageTitle", "Login");
//	            return "layout/layout";
//	        }
//	    }
	    
	    @PostMapping("/login")
	    public String loginUser(@RequestParam String username, 
	                           @RequestParam String password,
	                           HttpSession session,
	                           Model model) {
	        try {
	            User user = userService.authenticate(username, password);
	            if (user != null) {
	                session.setAttribute("currentUser", user);
	                
	                // Redirect based on role
	                if ("ADMIN".equalsIgnoreCase(user.getRole())) {
	                    return "redirect:/admin/dashboard";
	                } else {
	                    return "redirect:/";
	                }
	            } else {
	                model.addAttribute("error", "Invalid username or password");
	                model.addAttribute("user", new User());
	                model.addAttribute("contentPage", "/WEB-INF/views/login.jsp");
	                model.addAttribute("pageTitle", "Login");
	                return "layout/layout";
	            }
	        } catch (Exception e) {
	            model.addAttribute("error", "Login failed: " + e.getMessage());
	            model.addAttribute("user", new User());
	            model.addAttribute("contentPage", "/WEB-INF/views/login.jsp");
	            model.addAttribute("pageTitle", "Login");
	            return "layout/layout";
	        }
	    }
	 // =================== PROFILE ===================
	    @GetMapping("/profile")
	    public String viewProfile(Model model, HttpSession session) {
	        User currentUser = (User) session.getAttribute("currentUser");
	        if (currentUser == null) {
	            return "redirect:/user/login";
	        }
	        User user = userService.findByUsername(currentUser.getUsername());
	        model.addAttribute("user", user);
	        model.addAttribute("contentPage", "/WEB-INF/views/profile.jsp");
	        model.addAttribute("pageTitle", "My Profile");
	        return "layout/layout";
	    }

	    @PostMapping("/updateProfile")
	    public String updateProfile(@ModelAttribute User user, HttpSession session, Model model) {
	        User currentUser = (User) session.getAttribute("currentUser");
	        if (currentUser == null) {
	            return "redirect:/user/login";
	        }
	        
	        try {
	            User updatedUser = userService.updateUserProfile(currentUser.getUsername(), user);
	            session.setAttribute("currentUser", updatedUser); 
	            model.addAttribute("message", "Profile updated successfully!");
	            model.addAttribute("user", updatedUser);
	        } catch (Exception e) {
	            model.addAttribute("error", "Update failed: " + e.getMessage());
	            model.addAttribute("user", userService.findByUsername(currentUser.getUsername()));
	        }
	        
	        model.addAttribute("contentPage", "/WEB-INF/views/profile.jsp");
	        model.addAttribute("pageTitle", "My Profile");
	        return "layout/layout";
	    }

	    // =================== ADMIN VIEW ALL USERS ===================
	    @GetMapping("/list")
	    public String listUsers(Model model) {
	        model.addAttribute("users", userService.findAllUsers());
	        model.addAttribute("contentPage", "/WEB-INF/views/admin/adminUsers.jsp");
	        model.addAttribute("pageTitle", "All Users");
	        return "layout/layout";
	    }

	    //  =================== LOGOUT ===================
	    @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        session.invalidate();
	        return "redirect:/user/login";
	    }
	}

