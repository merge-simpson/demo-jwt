package com.example.demo.member.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

public final class AuthenticationDto {
    public record SignInRequestDto (
            @Email String email,
            @JsonProperty("password")
            @Pattern(regexp = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,100}$")
            String rawPassword
    ) {}

    @Builder
    public record SignInResponseDto (
            @JsonInclude(Include.NON_DEFAULT) Boolean requiresMfa,
            @JsonInclude(Include.NON_EMPTY) String token
    ) {}
}
