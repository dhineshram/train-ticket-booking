package com.cloudbees.trainbooking.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends Exception {

    public CustomException() {
        super();
    }
    public CustomException(String message) {
        super(message);
    }
    public CustomException(Throwable t) {
        super(t);
    }

    public abstract HttpStatus getStatus();

}
