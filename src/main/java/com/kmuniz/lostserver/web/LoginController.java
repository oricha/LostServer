package com.kmuniz.lostserver.web;

import com.kmuniz.lostserver.data.User;
import com.kmuniz.lostserver.repository.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // Constructor-based dependency injection
    @Autowired
    public LoginController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Return login.html
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // Return register.html
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        // Save the plain text password directly (NOT RECOMMENDED)
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("message", "Registration successful! Please login.");
        return "redirect:/login";
    }
    @RolesAllowed("ROLE_USER")
    @GetMapping("/profile")
    public String getUserProfile(Model model, HttpSession session) {
        // Retrieve the user from the session
        User user = (User) session.getAttribute("user");

        // If user is not found in the session, throw an exception
        if (user == null) {
            throw new IllegalArgumentException("User not found in session");
        }

        // Add the user details to the model for the view
        model.addAttribute("user", user);
        return "users"; // Return the profile view
    }

    @GetMapping("/logout-success")
    public String logoutSuccess(Model model) {
        model.addAttribute("message", "You have successfully logged out.");
        return "login"; // Redirect to the login page with a message
    }
}