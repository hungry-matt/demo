package com.example.demo.interfaces;

import com.example.demo.application.ReviewService;
import com.example.demo.domain.Review;
import com.example.demo.interfaces.review.ReviewRequestDto;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> created(@PathVariable("restaurantId") Long id
    , @Valid @RequestBody ReviewRequestDto resource
    , Authentication authentication) throws URISyntaxException {
        //custom filter에서 principal 정보를 가지고 있는
        //Authentication을 구현한 UsernamePasswordAuthenticationToken 객체를 생성

        //인증 정보
        Claims claims = (Claims) authentication.getPrincipal();
        //인증된 사용자 이름
        String name = claims.get("name", String.class);

        Review review = reviewService.addReview(id, Review.builder()
            .name(name)
            .restaurantId(id)
            .score(resource.getScore())
            .description(resource.getDescription())
            .build()
        );

        return ResponseEntity.created(new URI("/restaurants/" + id + "/reviews/" + review.getId())).body("{}");
    }

    @GetMapping("/restaurants/{restaurantId}/reviews")
    public List<Review> list(@PathVariable("restaurantId") Long id) {
        return reviewService.getReviews(id);
    }
}
