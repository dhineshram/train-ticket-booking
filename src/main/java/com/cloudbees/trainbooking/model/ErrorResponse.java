package com.cloudbees.trainbooking.model;

import java.time.OffsetDateTime;

public class ErrorResponse {

    private String message;
    private OffsetDateTime timestamp;

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = OffsetDateTime.now();
    }

}
