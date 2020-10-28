package com.example.demo.application;

public class PasswordWrongException extends RuntimeException{
    public PasswordWrongException() {
        super("Password is wrong");
    }
}
