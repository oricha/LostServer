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
            user1.setEmail("alice@example.com");

            User user2 = new User();
            user2.setName("Bob");
            user2.setEmail("bob@example.com");

            User user3 = new User();
            user3.setName("Admin");
            user3.setEmail("admin@admin.com");

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            System.out.println("Preloaded users into the database");
        }
    }
}