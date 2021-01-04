package com.example.demo.interfaces;

import com.example.demo.application.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void getReservations() throws Exception {

        Long restaurantId = 1L;

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MiwibmFtZSI6ImNlbyIsInJlc3RhdXJhbnRJZCI6MX0.unMWrIb5q7_XAw5LSpFLcgNIeCXb37pDD2GGvvhQdUU";

        mvc.perform(get("/reservations")
            .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk());

        verify(reservationService).getReservations(restaurantId);
    }
}
