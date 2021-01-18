package com.outofmemory.exception.auth;

import com.outofmemory.exception.CustomUserException;

public class PermissionException extends CustomUserException {
    public PermissionException() {
    }

    public PermissionException(String message) {
        super(message);
    }
}
