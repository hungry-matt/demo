package com.example.demo.interfaces;

import com.example.demo.application.UserService;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 1. User List
    // 2. User create -> 회원가입
    // 3. User update
    // 4. User delete -> level: 0 => 아무 것도 못 함.
    // (1: customer, 2: restaurant owner ,3 : admin)

    @GetMapping("/users")
    public List<User> list() {
        List<User> users = userService.getUsers();
        return users;
    }
}
