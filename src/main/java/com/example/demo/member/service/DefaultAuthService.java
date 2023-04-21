package com.example.demo.member.service;

import com.example.demo.member.api.dto.AuthenticationDto.SignInRequestDto;
import com.example.demo.member.api.dto.AuthenticationDto.SignInResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {

    @Override
    public SignInResponseDto signIn(SignInRequestDto body) {
        // TODO 이곳에서 인증을 수행
        return null;
    }
}
