package com.example.demo.interfaces;

import com.example.demo.application.ReservationService;
import com.example.demo.domain.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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

    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAwNCwibmFtZSI6IkpvaG4ifQ.e7Di1AdEZKKnbrMqLxdXChp9ds35Tqn5OFmsxMY1uE8";

    @Test
    public void create() throws Exception {

        Reservation mockReservation = Reservation.builder()
                .id(1L)
                .build();

        given(reservationService.addReservation(any(), any(), any(), any(),any(), any()))
                .willReturn(mockReservation);

        mvc.perform(post("/restaurants/1/reservations")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"date\" : \"2020-01-01\", \"time\" : \"13:00\", \"partySize\" : 20}")
                )
                .andExpect(status().isCreated());

        Long userId = 1004L;
        String name = "John";
        String date = "2020-01-01";
        String time = "13:00";
        Integer partySize = 20;

        verify(reservationService).addReservation(1L, userId, name, date, time, partySize);
    }

    @Test
    public void list() throws Exception {
        //손님이 등록한 예약 현황을 조회 한다.
        mvc.perform(get("/restaurants/reservations")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        Long userId = 1004L;

        verify(reservationService).getReservations(userId);
    }
}