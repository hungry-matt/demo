package com.example.demo.application;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String name, String email, String password) {

        Optional<User> existed = userRepository.findByEmail(email);

        //Optional 객체에 저장된 값이 존재하면 true, 존재하지 않으면 false 반환.
        if(existed.isPresent()) {
            throw new EmailExistedException(email);
        }

        String encodedPassword =  passwordEncoder.encode(password);

        User user = User.builder()
                .level(1L)
                .name(name)
                .email(email)
                .password(encodedPassword)
                .build();

        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotExistedException(email));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new PasswordWrongException();
        }

        return user;
    }
}
