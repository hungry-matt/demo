package com.example.demo.application;

import com.example.demo.domain.RestaurantRepository;
import com.example.demo.domain.Review;
import com.example.demo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void addReview(Review review) {
        reviewRepository.save(review);
    }
}
