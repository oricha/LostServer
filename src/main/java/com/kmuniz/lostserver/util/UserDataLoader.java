package com.kmuniz.lostserver.util;


import com.kmuniz.lostserver.data.User;
import com.kmuniz.lostserver.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    @PostConstruct
    public void preloadUsers() {
        if (userRepository.count() == 0) {
            User user1 = userFactory.createUser("Alice", "1001", "1", "alice@example.com", "user");
            User user2 = userFactory.createUser("Bob", "1002", "2", "bob@example.com", "user");
            User user3 = userFactory.createUser("Admin", "admin", "admin", "admin@admin.com", "admin");

            userRepository.saveAll(Arrays.asList(user1, user2, user3));
            System.out.println("Preloaded users into the database");
        }
    }
}