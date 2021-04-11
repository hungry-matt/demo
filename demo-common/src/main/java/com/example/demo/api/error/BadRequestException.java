package com.example.demo.api.error;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super("BadRequestException " + message);
    }
}
