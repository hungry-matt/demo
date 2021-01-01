package com.example.demo.interfaces.review;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReviewRequestDto {

    @NotNull
    private Integer score;

    @NotEmpty
    private String description;

    public ReviewRequestDto() {}

    @Builder
    public ReviewRequestDto(Integer score, String description) {
        this.score = score;
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public String getDescription() {
        return description;
    }
}
