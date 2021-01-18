package com.outofmemory.exception.auth;

import com.outofmemory.exception.CustomUserException;

public class RefreshTokenException extends CustomUserException {
    public RefreshTokenException() {
    }

    public RefreshTokenException(String message) {
        super(message);
    }
}
