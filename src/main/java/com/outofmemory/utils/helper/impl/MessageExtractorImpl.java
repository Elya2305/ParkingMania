package com.outofmemory.utils.helper.impl;

import com.cloudinary.utils.StringUtils;
import com.outofmemory.utils.helper.ErrorMessage;
import com.outofmemory.utils.helper.MessageExtractor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class MessageExtractorImpl implements MessageExtractor {
    private static final String DEFAULT_REG_MESSAGE = "An error occurred during registration";
    private static final String DEFAULT_LOGIN_MESSAGE = "An error occurred during login";
    private static final String DEFAULT_REFRESH_TOKEN_MESSAGE = "An error occurred during refreshing token";

    @Override
    public String extractRegErrorMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return DEFAULT_REG_MESSAGE;
        }
        return extractMessage(RegistrationError.values(), message).orElse(DEFAULT_LOGIN_MESSAGE);
    }

    @Override
    public String extractLoginErrorMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return DEFAULT_LOGIN_MESSAGE;
        }
        return extractMessage(LoginError.values(), message).orElse(DEFAULT_LOGIN_MESSAGE);
    }

    @Override
    public String extractRefreshTokenErrorMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return DEFAULT_REFRESH_TOKEN_MESSAGE;
        }
        return extractMessage(RefreshTokenError.values(), message).orElse(DEFAULT_REFRESH_TOKEN_MESSAGE);
    }

    private Optional<String> extractMessage(ErrorMessage[] errors, String message) {
        return Arrays.stream(errors).
                filter(o -> message.contains(o.name()))
                .map(ErrorMessage::getMessage)
                .findFirst();
    }

    @Getter
    private enum RegistrationError implements ErrorMessage {
        EMAIL_EXISTS("Email already exists"),
        OPERATION_NOT_ALLOWED("Operation is not allowed"),
        TOO_MANY_ATTEMPTS_TRY_LATER("Too many attempts. Please try later");

        RegistrationError(String message) {
            this.message = message;
        }

        private final String message;
    }

    @Getter
    private enum LoginError implements ErrorMessage {
        EMAIL_NOT_FOUND("Email is not found"),
        INVALID_PASSWORD("Password is invalid"),
        USER_DISABLED("User is disabled");

        LoginError(String message) {
            this.message = message;
        }

        private final String message;
    }

    @Getter
    private enum RefreshTokenError implements ErrorMessage {
        TOKEN_EXPIRED("The user's credential is no longer valid. Please sign in again."),
        USER_DISABLED("The user account has been disabled by an administrator"),
        USER_NOT_FOUND("The user corresponding to the refresh token was not found. It is likely the user was deleted."),
        INVALID_REFRESH_TOKEN("An invalid refresh token is provided.");

        RefreshTokenError(String message) {
            this.message = message;
        }

        private final String message;
    }
}
