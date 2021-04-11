package com.example.demo.api.error;

import com.example.demo.api.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private ApiResult<?> resultResponse(Throwable throwable, HttpStatus status) {
        return ApiResult.ERROR(throwable, status);
    }

    @ExceptionHandler(ServiceRuntimeException.class)
    public ApiResult<?> serviceRuntimeException(ServiceRuntimeException e) {
        log.warn("Service Runtime exception: {}", e.getMessage());
        return resultResponse(e, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BadRequestException.class)
    public ApiResult<?> RuntimeException(BadRequestException e) {
        log.warn("Bad Request exception: {}", e.getMessage());
        return resultResponse(e, HttpStatus.BAD_REQUEST);
    }
}
