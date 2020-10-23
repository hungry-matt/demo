package com.example.demo.application;

import com.example.demo.domain.RestaurantRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String name, String email, String password) {

        User user = User.builder()
                .level(1L)
                .name(name)
                .email(email)
                .password(password)
                .build();

        return userRepository.save(user);
    }
}
