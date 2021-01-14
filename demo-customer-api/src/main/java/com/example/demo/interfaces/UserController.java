package com.example.demo.interfaces;

import com.example.demo.application.UserService;
import com.example.demo.domain.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> create(
            @RequestBody User resource
    ) throws URISyntaxException {

        User user = userService.registerUser(resource.getName(), resource.getEmail(), resource.getPassword());

        String url = "/users/" + user.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @GetMapping("/me")
    public String me(Authentication authentication) {
        Claims claims = (Claims) authentication.getPrincipal();

        String name = claims.get("name", String.class);

        return name;
    }
}
