package com.example.demo.application;

import com.example.demo.domain.Review;
import com.example.demo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long id, Review review) {
        review.setRestaurantId(id);
        return reviewRepository.save(review);
    }
}
