package com.kmuniz.lostserver.util;

import com.kmuniz.lostserver.data.User;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

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
