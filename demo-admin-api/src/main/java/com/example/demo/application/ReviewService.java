package com.example.demo.application;

import com.example.demo.domain.Review;
import com.example.demo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews;
    }
}
