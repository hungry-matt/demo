package com.example.demo.api;

import org.springframework.http.HttpStatus;

public class ApiError {
    //오류메세지
    private final String message;

    //HTTP 오류 코드
    private final int status;

    public ApiError(Throwable throwable) {
        this(throwable.getMessage(), HttpStatus.BAD_REQUEST);
    }

    public ApiError(Throwable throwable, HttpStatus status) {
        this(throwable.getMessage(), status);
    }

    public ApiError(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

}

