package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private Long restaurantId;

    private Long userId;

    private String name;

    @NotEmpty
    private String date;

    @NotEmpty
    private String time;

    @NotNull
    private Integer partySize;

    private LocalDateTime createAt;

    public Reservation() {
    }

    @Builder
    private Reservation(Long id, Long restaurantId, Long userId, String name, @NotEmpty String date, @NotEmpty String time, @NotNull Integer partySize, LocalDateTime createAt) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.partySize = partySize;
        this.createAt = createAt;
    }
}
