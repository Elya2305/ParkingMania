package com.outofmemory.web;

import com.outofmemory.exception.CustomUserException;
import com.outofmemory.exception.auth.RefreshTokenException;
import com.outofmemory.exception.auth.TokenExpiredException;
import com.outofmemory.utils.api.ApiResponse;
import com.outofmemory.utils.api.Responses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    private static final String SOMETHING_WENT_WRONG = "Something went wrong";


    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleCustomException(Exception ex) {
        log.error("Was caught exception!", ex);
        return Responses.errorResponse(SOMETHING_WENT_WRONG);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ApiResponse<String> tokenExpiredException(TokenExpiredException ex) {
        log.error("Was caught exception!", ex);
        return Responses.tokenExpiredResponse(ex.getMessage());
    }

    @ExceptionHandler(RefreshTokenException.class)
    public ApiResponse<String> refreshTokenException(RefreshTokenException ex) {
        log.error("Was caught exception!", ex);
        return Responses.authErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(CustomUserException.class)
    public ApiResponse<String> handleUploadFileException(CustomUserException ex) {
        log.error("Was caught exception!", ex);
        return Responses.errorResponse(ex.getMessage());
    }
}
