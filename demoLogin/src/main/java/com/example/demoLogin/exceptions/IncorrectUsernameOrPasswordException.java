package com.example.demoLogin.exceptions;

public class IncorrectUsernameOrPasswordException extends RuntimeException {
    public IncorrectUsernameOrPasswordException(String message) {
        super(message);
    }
}
