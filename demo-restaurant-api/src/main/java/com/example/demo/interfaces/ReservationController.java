package com.example.demo.interfaces;

import com.example.demo.application.ReservationService;
import com.example.demo.domain.Reservation;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    //예약 조회
    //로그인 사용자의 매장 예약 현황을 조회할 수 있다.
    @GetMapping("/reservations")
    public List<Reservation> getReservations(
            Authentication authentication
    ) {

         Claims claims = (Claims) authentication.getPrincipal();

         Long restaurantId = claims.get("restaurantId", Long.class);

         return reservationService.getReservations(restaurantId);

    }
}
