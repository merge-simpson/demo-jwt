package com.example.demo.member.api;

import com.example.demo.member.api.dto.AuthenticationDto.SignInRequestDto;
import com.example.demo.member.api.dto.AuthenticationDto.SignInResponseDto;
import com.example.demo.member.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class AuthenticationApi {
    private final AuthService authService;

    @PostMapping("/sign-in")
    public SignInResponseDto signIn(
            @RequestBody @Valid SignInRequestDto body,
            HttpServletRequest request) {
        return authService.signIn(body);
    }
}
