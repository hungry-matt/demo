package com.example.demo.application;

import com.example.demo.application.ReviewService;
import com.example.demo.domain.Review;
import com.example.demo.domain.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void addReview() {
        Review review = Review.builder()
                .name("joker")
                .score(3)
                .description("test")
                .build();

        reviewService.addReview(1004L, review);

        verify(reviewRepository).save(any());
    }

    @Test
    public void getReviews() {
        List<Review> mockReviews = new ArrayList<>();
        Long id = 1L;

        mockReviews.add(Review.builder()
                .name("lee")
                .restaurantId(id)
                .score(3)
                .description("test")
                .build()
        );

        mockReviews.add(Review.builder()
                .name("kim")
                .restaurantId(id)
                .score(5)
                .description("test")
                .build()
        );

        given(reviewRepository.findAllByRestaurantId(id)).willReturn(mockReviews);

        List<Review> reviews = reviewService.getReviews(id);

        Review review = reviews.get(0);

        assertThat(review.getRestaurantId(), is(id));

        verify(reviewRepository).findAllByRestaurantId(id);
    }
}