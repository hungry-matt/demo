package com.example.demo.application;

import com.example.demo.domain.Reservation;
import com.example.demo.domain.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation addReservation(Long restaurantId, Long userId, String name, String date, String time, Integer partySize) {

        Reservation reservation  = Reservation.builder()
                .restaurantId(restaurantId)
                .userId(userId)
                .name(name)
                .date(date)
                .time(time)
                .partySize(partySize)
                .build();

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservations(Long userId) {
        return reservationRepository.findAllByUserId(userId);
    }
}
