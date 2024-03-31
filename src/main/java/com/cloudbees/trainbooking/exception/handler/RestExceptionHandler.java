package com.cloudbees.trainbooking.exception.handler;

import com.cloudbees.trainbooking.exception.CustomException;
import com.cloudbees.trainbooking.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e, WebRequest r) {
        var response = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, e.getStatus());
    }

}
