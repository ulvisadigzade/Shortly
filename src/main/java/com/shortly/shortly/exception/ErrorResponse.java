package com.shortly.shortly.exception;

import lombok.Getter;

import java.time.Instant;

@Getter
public class ErrorResponse {
    private final String code;        // Machine-readable error code
    private final String message;     // Human-readable message
    private final int status;         // HTTP status code
    private final Instant timestamp;  // When error occurred

    public ErrorResponse(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.timestamp = Instant.now(); //Current time;
    }

}
