package com.kmuniz.lostserver.web;

import com.kmuniz.lostserver.data.User;
import com.kmuniz.lostserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/")
    public String uploadPage() {
        return "redirect:/upload.html";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        // Fetch all users from the database
        List<User> users = userRepository.findAll();

        // Add the users list to the model to be accessed in the view
        model.addAttribute("users", users);

        // Return the users.html view
        return "users"; // The name of the HTML template
    }
}
