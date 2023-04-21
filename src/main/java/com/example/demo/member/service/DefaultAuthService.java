package com.example.demo.member.service;

import com.example.demo.member.api.dto.AuthenticationDto.SignInRequestDto;
import com.example.demo.member.api.dto.AuthenticationDto.SignInResponseDto;
import com.example.demo.util.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public SignInResponseDto signIn(SignInRequestDto body) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(body.email(), body.rawPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String token = jwtTokenProvider.generate(body.email());

        return SignInResponseDto.builder()
                .requiresMfa(false)
                .token(token)
                .build();
    }
}
