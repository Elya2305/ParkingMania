package com.outofmemory.exception;

public class CustomUserException extends RuntimeException {
    public CustomUserException() {
    }

    public CustomUserException(String message) {
        super(message);
    }
}
