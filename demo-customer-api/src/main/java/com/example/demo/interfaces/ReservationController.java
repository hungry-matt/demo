package com.example.demo.interfaces;

import com.example.demo.application.ReservationService;
import com.example.demo.domain.Reservation;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    //예약 등록
    //손님이 매장 예약을 등록 한다.
    @PostMapping("/restaurants/{restaurantId}/reservations")
    public ResponseEntity<?> create(
            @PathVariable Long restaurantId,
            @Valid @RequestBody Reservation resource,
            Authentication authentication
            ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();

        Long userId = claims.get("id", Long.class);
        String name = claims.get("name", String.class);

        Reservation reservation = reservationService.addReservation(restaurantId, userId, name, resource.getDate(), resource.getTime(), resource.getPartySize());

        String url = "/restaurant/" + restaurantId + "/reservations/" + reservation.getId();

        return ResponseEntity.created(new URI(url)).body("{}");
    }

    //손님이 등록한 예약 현황을 조회 한다.
    @GetMapping("/restaurants/reservations")
    public List<Reservation> list(
            Authentication authentication
    ) {

        Claims claims = (Claims) authentication.getPrincipal();

        Long userId = claims.get("id", Long.class);

        return reservationService.getReservations(userId);
    }
}
