package com.example.demo.member.exception;

import com.example.demo.support.exception.CustomException;
import com.example.demo.support.exception.DefaultErrorCode;

public class AuthenticationException extends CustomException {

    public final AuthenticationErrorCode ERROR_CODE;

    public AuthenticationException() {
        super(DefaultErrorCode.DEFAULT.defaultMessage());
        this.ERROR_CODE = AuthenticationErrorCode.DEFAULT;
    }

    public AuthenticationException(AuthenticationErrorCode errorCode) {
        super(errorCode.defaultMessage());
        this.ERROR_CODE = errorCode;
    }

    public AuthenticationException(String message) {
        super(message);
        this.ERROR_CODE = AuthenticationErrorCode.DEFAULT;
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
        this.ERROR_CODE = AuthenticationErrorCode.DEFAULT;
    }

    public AuthenticationException(AuthenticationErrorCode errorCode, Throwable cause) {
        super(errorCode.defaultMessage(), cause);
        this.ERROR_CODE = errorCode;
    }
}
