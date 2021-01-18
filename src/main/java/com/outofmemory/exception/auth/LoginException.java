package com.outofmemory.exception.auth;

import com.outofmemory.exception.CustomUserException;

public class LoginException extends CustomUserException {
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
