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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
        given(reviewService.addReview(eq(1L), any())).willReturn(
                Review.builder()
                    .id(1004L)
                    .build()
        );

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAwNCwibmFtZSI6IkpvaG4ifQ.e7Di1AdEZKKnbrMqLxdXChp9ds35Tqn5OFmsxMY1uE8";

        mvc.perform(post("/restaurants/1/reviews/")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"score\" : \"3\", \"description\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("{}"))
                .andExpect(header().string("location", "/restaurants/1/reviews/1004"));

        verify(reviewService).addReview(eq(1L), any());
    }

    @Test
    public void notCreated() throws Exception {
        mvc.perform(post("/restaurants/1/reviews/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

        verify(reviewService, never()).addReview(eq(1L), any());
    }
}