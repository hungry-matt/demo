package com.example.demo.application;

import com.example.demo.domain.RestaurantRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String name, String email, String password) {
        Optional<User> existed = userRepository.findByEmail(email);

        if(existed.isPresent()) {
            throw new EmailExistedException(email);
        }

        PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
        String encodedPassword =  passwordEncoder.encode(password);

        User user = User.builder()
                .level(1L)
                .name(name)
                .email(email)
                .password(encodedPassword)
                .build();

        return userRepository.save(user);
    }
}
