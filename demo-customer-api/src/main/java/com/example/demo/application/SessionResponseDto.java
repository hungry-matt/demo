package com.example.demo.application;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponseDto {

    private String accessToken;
}
