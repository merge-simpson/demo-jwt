package com.example.demo.member.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public final class AuthenticationDto {
    public record SignInRequestDto (
            @Email String email,
            @JsonProperty("password")
            @Pattern(regexp = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,100}$")
            String rawPassword
    ) {}

    public record SignInResponseDto (
            @JsonInclude(Include.NON_DEFAULT) Boolean requires2Fa,
            @JsonInclude(Include.NON_EMPTY) String token
    ) {}
}
