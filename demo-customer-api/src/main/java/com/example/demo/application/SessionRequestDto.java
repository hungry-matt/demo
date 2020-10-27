package com.example.demo.application;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionRequestDto {

    private String email;

    private String password;
}
