package com.example.demo.interfaces;

import com.example.demo.application.ReviewService;
import com.example.demo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void created() throws Exception {
        given(reviewService.addReview(any())).willReturn(
                Review.builder()
                .id(1004L)
                .build()
        );

        mvc.perform(post("/restaurants/1/reviews/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"joker\", \"score\" : \"3\", \"description\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("{}"))
                .andExpect(header().string("location", "/restaurants/1/reviews/1004"));

        verify(reviewService).addReview(any());
    }

    @Test
    public void notCreated() throws Exception {
        mvc.perform(post("/restaurants/1/reviews/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

        verify(reviewService, never()).addReview(any());
    }
}