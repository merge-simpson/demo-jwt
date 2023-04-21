package com.example.demo.member.service;

import com.example.demo.member.api.dto.AuthenticationDto.SignInRequestDto;
import com.example.demo.member.api.dto.AuthenticationDto.SignInResponseDto;

public interface AuthService {

    SignInResponseDto signIn(SignInRequestDto body);
}
