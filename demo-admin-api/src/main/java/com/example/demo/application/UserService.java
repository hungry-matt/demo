package com.example.demo.application;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User addUser(String name, String email, String password) {

        String encodePassword = passwordEncoder.encode(password);

        User user = User.builder()
                    .name(name)
                    .email(email)
                    .password(encodePassword)
                    .level(1L)
                    .build();

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, String name, String email, Long level) {
        User user = userRepository.findById(id).orElse(null);

        user.setName(name);
        user.setEmail(email);
        user.setLevel(level);

        return user;
    }

    @Transactional
    public User deactivateUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        user.deactivate();

        return user;
    }
}
