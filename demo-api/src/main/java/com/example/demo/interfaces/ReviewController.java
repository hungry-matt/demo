package com.example.demo.interfaces;

import com.example.demo.application.ReviewService;
import com.example.demo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("restaurants/{restaurantId}/reviews")
    public ResponseEntity created(@PathVariable("restaurantId") Long id
    ,@Valid @RequestBody Review resource) throws URISyntaxException {

        resource.setRestaurantId(id);

        Review review = reviewService.addReview(resource);

        return ResponseEntity.created(new URI("/restaurants/" + id + "/reviews/" + review.getId())).body("{}");
    }
}
