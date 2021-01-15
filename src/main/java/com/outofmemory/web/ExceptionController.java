package com.outofmemory.web;

import com.outofmemory.excetion.UploadFileException;
import com.outofmemory.excetion.ValidationException;
import com.outofmemory.excetion.auth.LoginException;
import com.outofmemory.excetion.auth.RegistrationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ExceptionController {
    private static final String SOMETHING_WENT_WRONG = "Something went wrong";


    @ExceptionHandler(Exception.class)
    public String handleCustomException(Exception ex) {
        log.error("Was caught exception!", ex);
        return SOMETHING_WENT_WRONG;
    }

    @ExceptionHandler(UploadFileException.class)
    public String handleUploadFileException(UploadFileException ex) {
        log.error("Was caught exception!", ex);
        return ex.getMessage();
    }

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex) {
        log.error("Was caught exception!", ex);
        return ex.getMessage();
    }

    @ExceptionHandler(RegistrationException.class)
    public String handleRegistrationException(RegistrationException ex) {
        log.error("Was caught exception!", ex);
        return ex.getMessage();
    }

    @ExceptionHandler(LoginException.class)
    public String handleLoginException(LoginException ex) {
        log.error("Was caught exception!", ex);
        return ex.getMessage();
    }
}
