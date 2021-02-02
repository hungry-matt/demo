package com.example.demo.api.error;

/**
 * @Description : Custom Runtime Exception
 * Service단에서 발생한 오류 처리시 사용
 * @author y
 * @since 2020.11
 * @version 1.0
 */

public class ServiceRuntimeException extends RuntimeException {

    public ServiceRuntimeException(String message) {
        super("ServiceRuntimeException: " + message);
    }
}
