package com.example.demo.application;

import com.example.demo.domain.Reservation;
import com.example.demo.domain.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class ReservationServiceTest {


    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void addReservation() {
        Long restaurantId = 1L;
        Long userId = 1004L;
        String name = "John";
        String date = "2020-01-01";
        String time = "13:00";
        Integer partySize = 20;

        given(reservationRepository.save(any()))
                .will(invocation -> {
                    Reservation reservation = invocation.getArgument(0);
                    return reservation;
                });

        Reservation reservation = reservationService.addReservation(restaurantId, userId, name, date, time, partySize);

        assertThat(reservation.getName(), is(name));

        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    public void getResevations() {

        Long userId = 1004L;

        given(reservationRepository.findAllByUserId(userId)).will(invocation -> {
            List<Reservation> reservationList =  new ArrayList<>();

            reservationList.add(Reservation.builder()
                    .userId(userId)
                    .build());

            return reservationList;
         });

        List<Reservation> reservationList = reservationService.getReservations(userId);

        Reservation reservation = reservationList.get(0);

        assertThat(userId, is(reservation.getUserId()));

        verify(reservationRepository).findAllByUserId(userId);

    }
}