package com.kmuniz.lostserver.util;


import com.kmuniz.lostserver.data.User;
import com.kmuniz.lostserver.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * UserDataLoader is responsible for preloading default user data into the database
 * during the application startup phase.
 *
 * This class uses the Factory Pattern via the UserFactory to create user objects
 * with predefined configurations. It ensures that the user creation logic is
 * centralized and reusable, adhering to best practices in clean code and separation
 * of concerns.
 *
 * The @Component annotation registers this class as a Spring-managed bean, and
 * the @PostConstruct annotation ensures that the preloadUsers method is executed
 * after the bean initialization.
 */
@Component
public class UserDataLoader {


    // Logger instance to log information and errors
    private static final Logger logger = Logger.getLogger(UserDataLoader.class.getName());

    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final BCryptPasswordEncoder passwordEncoder;

    // Constructor-based injection
    @Autowired
    public UserDataLoader(UserRepository userRepository, UserFactory userFactory, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Preloads default users if the user repository is empty.
     */
    @PostConstruct
    public void preloadUsers() {
        try {
            // Check if the repository is empty
            if (userRepository.count() == 0) {
                // Create default users with hashed passwords
                User user1 = createUser("Alice", "1001", "1", "alice@example.com", "user");
                User user2 = createUser("Bob", "1002", "2", "bob@example.com", "user");
                User user3 = createUser("Admin", "admin", "admin", "admin@admin.com", "admin");

                // Save the users to the repository
                userRepository.saveAll(Arrays.asList(user1, user2, user3));
                logger.info("Preloaded users into the database");
            }
        } catch (Exception e) {
            // Log any errors that occur during the preload process
            logger.severe("Error preloading users: " + e.getMessage());
        }
    }

    /**
     * Creates a user with a hashed password.
     *
     * @param name     The name of the user
     * @param username The username of the user
     * @param password The password to hash and set for the user
     * @param email    The email of the user
     * @param role     The role of the user (e.g., 'user', 'admin')
     * @return A new User object
     */
    private User createUser(String name, String username, String password, String email, String role) {
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(password);

        // Create and return a new user with hashed password
        return userFactory.createUser(name, username, hashedPassword, email, role);
    }
}