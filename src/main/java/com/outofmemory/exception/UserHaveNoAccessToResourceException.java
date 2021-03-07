package com.outofmemory.exception;

public class UserHaveNoAccessToResourceException extends CustomUserException {
    public UserHaveNoAccessToResourceException() {
    }

    public UserHaveNoAccessToResourceException(String message) {
        super(message);
    }
}
