package com.example.demo.application;

import com.example.demo.domain.Reservation;
import com.example.demo.domain.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ReservationServiceTest {

    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void getReservations() {

        Long restaurantId = 1L;

        given(reservationRepository.findAllByRestaurantId(restaurantId)).will(invocation -> {
            List<Reservation> reservationList =  new ArrayList<>();

            reservationList.add(Reservation.builder()
                    .restaurantId(restaurantId)
                    .build()
            );

            return reservationList;
        });

        List<Reservation> reservationList = reservationService.getReservations(restaurantId);

        Reservation reservation = reservationList.get(0);

        assertThat(reservation.getRestaurantId(), is(restaurantId));

        verify(reservationRepository).findAllByRestaurantId(any());

    }
}