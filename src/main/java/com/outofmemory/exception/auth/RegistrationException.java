package com.outofmemory.exception.auth;

import com.outofmemory.exception.CustomUserException;

public class RegistrationException extends CustomUserException {
    public RegistrationException() {
    }

    public RegistrationException(String message) {
        super(message);
    }
}
