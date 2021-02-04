package com.example.demo.api;

import org.springframework.http.HttpStatus;

public class ApiResult<T> {
    //API 요청 처리 결과
    private final boolean success;

    //success가 true라면 API 요청 처리 응답값
    private final T response;

    //success가 false라면 API 요청 처리 응답값
    private final ApiError error;

    //success가 true라면 message 처리 응답값
    private final String message;

    private ApiResult(boolean success) {
        this(success, null, null, null);
    }

    private ApiResult(boolean success, T response) {
        this(success, response, null, null);
    }

    private ApiResult(boolean success, T response, String message) {
        this(success, response, null, message);
    }

    private ApiResult(boolean success, ApiError error) {
        this(success, null, error, null);
    }

    private ApiResult(boolean success, T response, ApiError error, String message) {
        this.success = success;
        this.response = response;
        this.error = error;
        this.message = message;
    }

    //응답 성공시 요청 결과 처리
    public static <T> ApiResult<T> OK() {
        return new ApiResult<>(true);
    }

    //응답 성공시 요청 결과, 응답 데이터 처리
    public static <T> ApiResult<T> OK(T response) {
        return new ApiResult<>(true, response);
    }

    //응답 성공시 요청 결과, 응답 데이터, 메세지 처리
    public static <T> ApiResult<T> OK(T response, String message) {
        return new ApiResult<>(true, response, message);
    }

    //응답 실패시 요청 결과, 예외 메세지 처리
    public static <T> ApiResult<T> ERROR(Throwable throwable) {
        return new ApiResult<>(false, new ApiError(throwable));
    }

    //응답 실패시 요청 결과, 예외 메세지, HTTP 상태값 처리
    public static <T> ApiResult<T> ERROR(Throwable throwable, HttpStatus status) {
        return new ApiResult<>(false, new ApiError(throwable, status));
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResponse() {
        return response;
    }

    public ApiError getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

}
