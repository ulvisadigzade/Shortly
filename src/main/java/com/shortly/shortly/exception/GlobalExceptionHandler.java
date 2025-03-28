package com.shortly.shortly.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse>handleBusinessException(BusinessException ex){
        ErrorCode currentCode = ex.getErrorCode();
        String ErrorMessage = (ex.getErrorMessage()!=null?
                ex.getErrorMessage():currentCode.getMessage());

        ErrorResponse response = new ErrorResponse(
                currentCode.getCode(),
                ErrorMessage,
                currentCode.getStatus()
        );
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(currentCode.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String>handleValidationException(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        String errorMessage = "";

        for(FieldError e:errors){
            errorMessage = errorMessage + e.getDefaultMessage() + "\n";
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
