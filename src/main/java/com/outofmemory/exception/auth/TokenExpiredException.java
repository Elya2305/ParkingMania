package com.outofmemory.exception.auth;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
    }

    public TokenExpiredException(String message) {
        super(message);
    }
}
