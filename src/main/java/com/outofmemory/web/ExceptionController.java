package com.outofmemory.web;

import com.outofmemory.exception.CustomUserException;
import com.outofmemory.exception.UploadFileException;
import com.outofmemory.exception.UserHaveNoAccessToResourceException;
import com.outofmemory.exception.ValidationException;
import com.outofmemory.exception.auth.FirebaseTokenInvalidException;
import com.outofmemory.exception.auth.LoginException;
import com.outofmemory.exception.auth.RegistrationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    private static final String SOMETHING_WENT_WRONG = "Something went wrong";


    @ExceptionHandler(Exception.class)
    public String handleCustomException(Exception ex) {
        log.error("Was caught exception!", ex);
        return SOMETHING_WENT_WRONG;
    }

    @ExceptionHandler(CustomUserException.class)
    public String handleUploadFileException(CustomUserException ex) {
        log.error("Was caught exception!", ex);
        return ex.getMessage();
    }
}
