package com.example.demo.member.exception;

import com.example.demo.support.exception.CustomException;
import com.example.demo.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum AuthenticationErrorCode implements ErrorCode {
    MEMBER_MISSING("계정 없음", HttpStatus.NOT_FOUND),
    INVALID_CREDENTIALS("인증 정보 불일치", HttpStatus.FORBIDDEN),
    PLATFORM_BLOCKED("제한된 사용자", HttpStatus.FORBIDDEN),
    SLEEP("휴면 계정", HttpStatus.FORBIDDEN),
    REMOVED("탈퇴한 사용자", HttpStatus.GONE),
    DEFAULT("인증 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    final String MESSAGE;
    final HttpStatus STATUS;

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
        return new AuthenticationException();
    }

    @Override
    public CustomException defaultException(Throwable cause) {
        return null;
    }
}
