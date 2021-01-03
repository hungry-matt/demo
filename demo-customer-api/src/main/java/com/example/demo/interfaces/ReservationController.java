package com.example.demo.interfaces;

import com.example.demo.application.ReservationService;
import com.example.demo.domain.Reservation;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;


@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

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
}
