package com.example.demo.application;

import com.example.demo.domain.CategoryRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User addUser(String name, String email) {
        User user = User.builder()
                    .name(name)
                    .email(email)
                    .build();

        return userRepository.save(user);
    }

    public String updateUser(Long id, String name, String email, Long level) {
        return "";
    }
}
