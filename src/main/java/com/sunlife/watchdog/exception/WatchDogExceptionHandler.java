package com.labcorp.timeoff.exception;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class WatchDogExceptionHandler {

    @ExceptionHandler(WatchDogStatusException.class)
    public ResponseEntity<ErrorMessage> handleWatchDogStatusException(HttpServletRequest request,
                                                                         WatchDogStatusException e) {
        return new ResponseEntity<>(
               new ErrorMessage(e.getStatusCode()), e.getDetailMessage());
    }


}
