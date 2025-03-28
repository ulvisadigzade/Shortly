package com.shortly.shortly.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String errorMessage;

    //maybe use super
    public BusinessException(ErrorCode errorCode,String errorMessage){
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }
    public BusinessException(ErrorCode errorCode){
        this.errorCode=errorCode;
        this.errorMessage=null;
    }


}
