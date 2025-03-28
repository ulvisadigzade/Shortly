package com.shortly.shortly.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum ErrorCode {
    AlreadyExistsException(409,"ERR-RESOURCE-409","Resource already exists"),
    NotFoundException(404,"ERR-RESOURCE-404","Resource not found");


    private final int status;
    private final String code;
    private final String message;

}
