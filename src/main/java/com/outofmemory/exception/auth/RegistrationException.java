package com.outofmemory.exception.auth;

public class RegistrationException extends RuntimeException {
    public RegistrationException() {
    }

    public RegistrationException(String message) {
        super(message);
    }
}
