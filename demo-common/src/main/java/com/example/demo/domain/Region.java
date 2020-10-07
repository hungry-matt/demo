package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Region {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
