package com.outofmemory.exception.auth;

import com.outofmemory.exception.CustomUserException;

public class TokenExpiredException extends CustomUserException {
    public TokenExpiredException() {
    }

    public TokenExpiredException(String message) {
        super(message);
    }
}
