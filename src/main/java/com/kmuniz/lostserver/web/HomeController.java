package com.kmuniz.lostserver.web;

import com.kmuniz.lostserver.data.User;
import com.kmuniz.lostserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserRepository userRepository;

    // Constructor-based dependency injection
    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Redirects to the upload page.
     *
     * @return a redirect to the upload.html page
     */
    @GetMapping
    public String uploadPage() {
        return "redirect:/upload.html";
    }

    /**
     * Fetches and displays a list of users.
     *
     * @param model the model to pass data to the view
     * @return the name of the HTML template for displaying users
     */
    @GetMapping("/users")
    public String listUsers(Model model) {
        try {
            // Fetch all users from the database
            List<User> users = userRepository.findAll();

            // Add the users list to the model
            model.addAttribute("users", users);

            return "users"; // The name of the HTML template
        } catch (Exception e) {
            // Log the exception and redirect to an error page
            // Logging framework like SLF4J can be used here
            System.err.println("Error fetching users: " + e.getMessage());
            return "error"; // Redirect to a generic error page
        }
    }
}