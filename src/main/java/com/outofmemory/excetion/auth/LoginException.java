package com.outofmemory.excetion.auth;

public class LoginException extends RuntimeException {
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
