package com.kmuniz.lostserver.util;


import com.kmuniz.lostserver.data.User;
import com.kmuniz.lostserver.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserDataLoader {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void preloadUsers() {

        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setName("Alice");
            user1.setUsername("1001");
            user1.setPassword("1");
            user1.setEmail("alice@example.com");
            user1.setRole("user");

            User user2 = new User();
            user2.setName("Bob");
            user2.setUsername("1002");
            user2.setPassword("2");
            user2.setEmail("bob@example.com");
            user2.setRole("user");

            User user3 = new User();
            user3.setName("Admin");
            user3.setUsername("admin");
            user3.setPassword("admin");
            user3.setEmail("admin@admin.com");
            user3.setRole("admin");

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            System.out.println("Preloaded users into the database");
        }
    }
}