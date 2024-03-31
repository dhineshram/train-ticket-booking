package com.cloudbees.trainbooking.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DuplicateException extends CustomException {

    private final HttpStatus status;

    public DuplicateException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT;
    }
}
