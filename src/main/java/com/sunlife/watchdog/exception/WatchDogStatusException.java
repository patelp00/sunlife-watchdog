package com.labcorp.timeoff.exception;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

public class WatchDogStatusException extends RuntimeException {

    private int statusCode;

    public WatchDogStatusException(String errorMessage, int statusCode, Throwable cause) {
        super(errorMessage, cause);
        this.statusCode = statusCode;
    }

}
