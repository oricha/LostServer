package com.kmuniz.lostserver.util;

import com.kmuniz.lostserver.data.User;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    // The createUser method is a factory method that creates a new User object
    // with the provided attributes: name, username, password, email, and role.
    // This follows the Factory Design Pattern to encapsulate the object creation logic.

    /**
     * Creates a new User object with the provided attributes.
     *
     * @param name     the name of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param email    the email of the user
     * @param role     the role of the user (e.g., "admin", "user")
     * @return a new User object with the specified attributes
     */
    public User createUser(String name, String username, String password, String email, String role) {
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);
        return user;
    }
}
