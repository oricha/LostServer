package com.kmuniz.lostserver.web;

import com.kmuniz.lostserver.data.User;
import com.kmuniz.lostserver.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login"; // Return login.html
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        // Find the user by username
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            // If user is not found, add an error message and redirect to login page
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }

        User user = userOptional.get();

        // Check if the provided password matches the stored one
        if (!password.equals(user.getPassword())) {
            // If password doesn't match, add an error message and redirect to login page
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }

        // Store user details in the session
        session.setAttribute("user", user);

        // Redirect based on user role
        if ("ADMIN".equals(user.getRole())) {
            return "redirect:/admin"; // Example: admin page
        } else {
            return "redirect:/items"; // Redirect to items page for regular users
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // Return register.html
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        // Save the plain text password directly (NOT RECOMMENDED)
        user.setPassword(user.getPassword());
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("message", "Registration successful! Please login.");
        return "redirect:/login";
    }

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
}