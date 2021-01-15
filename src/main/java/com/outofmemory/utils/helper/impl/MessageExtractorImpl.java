package com.outofmemory.utils.helper.impl;

import com.cloudinary.utils.StringUtils;
import com.outofmemory.utils.helper.MessageExtractor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class MessageExtractorImpl implements MessageExtractor {
    private static final String DEFAULT_REG_MESSAGE = "An error occurred during registration";
    private static final String DEFAULT_LOGIN_MESSAGE = "An error occurred during login";

    @Override
    public String extractRegErrorMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return DEFAULT_REG_MESSAGE;
        }
        if (message.contains(RegistrationError.EMAIL_EXISTS.name())) {
            return RegistrationError.EMAIL_EXISTS.getMessage();
        }
        if (message.contains(RegistrationError.OPERATION_NOT_ALLOWED.name())) {
            return RegistrationError.OPERATION_NOT_ALLOWED.getMessage();
        }
        if (message.contains(RegistrationError.TOO_MANY_ATTEMPTS_TRY_LATER.name())) {
            return RegistrationError.TOO_MANY_ATTEMPTS_TRY_LATER.getMessage();
        }
        return DEFAULT_REG_MESSAGE;
    }

    @Override
    public String extractLoginErrorMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return DEFAULT_LOGIN_MESSAGE;
        }
        if (message.contains(LoginError.EMAIL_NOT_FOUND.name())) {
            return LoginError.EMAIL_NOT_FOUND.getMessage();
        }
        if (message.contains(LoginError.INVALID_PASSWORD.name())) {
            return LoginError.INVALID_PASSWORD.getMessage();
        }
        if (message.contains(LoginError.USER_DISABLED.name())) {
            return LoginError.USER_DISABLED.getMessage();
        }
        return DEFAULT_LOGIN_MESSAGE;
    }

    @Getter
    private enum RegistrationError {
        EMAIL_EXISTS("Email already exists"),
        OPERATION_NOT_ALLOWED("Operation is not allowed"),
        TOO_MANY_ATTEMPTS_TRY_LATER("Too many attempts. Please try later");

        RegistrationError(String message) {
            this.message = message;
        }

        private final String message;
    }

    @Getter
    private enum LoginError {
        EMAIL_NOT_FOUND("Email is not found"),
        INVALID_PASSWORD("Password is invalid"),
        USER_DISABLED("User is disabled");

        LoginError(String message) {
            this.message = message;
        }

        private final String message;
    }
}
