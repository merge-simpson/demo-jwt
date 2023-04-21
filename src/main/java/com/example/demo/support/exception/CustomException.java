package com.example.demo.support.exception;

public class CustomException extends RuntimeException {

    protected final ErrorCode ERROR_CODE;

    public CustomException() {
        super(DefaultErrorCode.DEFAULT.defaultMessage());
        this.ERROR_CODE = DefaultErrorCode.DEFAULT;
    }

    public CustomException(ErrorCode errorCode) {
        super(errorCode.defaultMessage());
        this.ERROR_CODE = errorCode;
    }

    public CustomException(String message) {
        super(message);
        this.ERROR_CODE = DefaultErrorCode.DEFAULT;
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.ERROR_CODE = DefaultErrorCode.DEFAULT;
    }

    public CustomException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.defaultMessage(), cause);
        this.ERROR_CODE = errorCode;
    }
}
