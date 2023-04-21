package com.example.demo.support.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum DefaultErrorCode implements ErrorCode {
    DEFAULT("예외 발생", HttpStatus.INTERNAL_SERVER_ERROR);

    public final String MESSAGE;
    public final HttpStatus STATUS;

    @Override
    public String getName() {
        return name();
    }

    @Override
    public HttpStatus defaultHttpStatus() {
        return STATUS;
    }

    @Override
    public String defaultMessage() {
        return MESSAGE;
    }

    @Override
    public CustomException defaultException() {
        return new CustomException(DEFAULT);
    }

    @Override
    public CustomException defaultException(Throwable cause) {
        return new CustomException(DEFAULT, cause);
    }
}
