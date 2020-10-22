package com.example.demo.application;

import com.example.demo.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User registerUser(String name, String email, String password) {

        User user = User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();

        return user;
    }
}
