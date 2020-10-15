package com.example.demo.interfaces;

import com.example.demo.application.UserService;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping("/users")
    public ResponseEntity<?> create(
            @RequestBody User resource
    ) throws URISyntaxException {
        User user = userService.addUser(resource.getName(), resource.getEmail());
        String url = "/users/" + user.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @PatchMapping("/users/{id}")
    public String update(
            @PathVariable("id") Long id
            , @RequestBody User resource
    ) {
        userService.updateUser(id, resource.getName(), resource.getEmail(), resource.getLevel());
        return "{}";
    }
}
