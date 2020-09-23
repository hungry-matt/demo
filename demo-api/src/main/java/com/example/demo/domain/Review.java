package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Review {

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;

    private String name;

    private int score;

    private String description;
}
