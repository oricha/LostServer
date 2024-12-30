package com.kmuniz.lostserver.web;

import com.kmuniz.lostserver.data.User;
import com.kmuniz.lostserver.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
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
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
        User user = userOptional.get();

        // Check if the provided password matches the stored password
        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }

        // Set user details in the session
        session.setAttribute("user", user);

        if (user.getRole().equals("ADMIN")) {
            return "redirect:/"; // Example admin upload file
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
//        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "Registration successful! Please login.");
        return "redirect:/login";
    }

//    @GetMapping("/profile")
//    public String getUserProfile(Model model) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        model.addAttribute("user", user);
//        return "profile"; // Return profile.html
//    }
}