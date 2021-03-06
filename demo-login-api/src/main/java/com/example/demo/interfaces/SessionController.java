package com.example.demo.interfaces;

import com.example.demo.api.ApiResult;
import com.example.demo.application.UserService;
import com.example.demo.domain.User;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import static com.example.demo.api.ApiResult.OK;

@CrossOrigin
@RestController
public class SessionController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/session")
    public ResponseEntity<SessionResponseDto> create(
            @RequestBody SessionRequestDto resource
    ) throws URISyntaxException {

        String email = resource.getEmail();
        String password = resource.getPassword();

        User user = userService.authenticate(email, password);

        String accessToken = jwtUtil.createToken(user.getId(), user.getName()
                    , user.isRestaurantOwner() ? user.getRestaurantId() : null);

        String url = "/session";
        return ResponseEntity.created(new URI(url))
                 .body(SessionResponseDto.builder()
                         .accessToken(accessToken)
                         .build());
    }

    @PostMapping("/session/admin")
    public ApiResult<?> createAdmin(
            @RequestBody SessionRequestDto requestDto
    ) {

        User user = userService.authenticateOwner(requestDto.getEmail(), requestDto.getPassword());

        String accessToken = jwtUtil.createToken(user.getId(), user.getName(), user.getRestaurantId());

        return OK(SessionResponseDto.builder()
                .accessToken(accessToken)
                .build());
    }
}
