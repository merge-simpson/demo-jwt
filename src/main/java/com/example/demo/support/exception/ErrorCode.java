package com.example.demo.support.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String getName();
    HttpStatus defaultHttpStatus();
    String defaultMessage();
    CustomException defaultException();
    CustomException defaultException(Throwable cause);
}
