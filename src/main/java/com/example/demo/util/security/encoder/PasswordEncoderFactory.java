package com.example.demo.util.security.encoder;

import com.example.demo.properties.PasswordEncoderProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public final class PasswordEncoderFactory {
    private final PasswordEncoderProperties properties;
    private final BcryptEncoder bcryptEncoder;
    private final ScryptEncoder scryptEncoder;
    private final Pbkdf2Encoder pbkdf2Encoder;

    @RequiredArgsConstructor
    public enum EncoderType {
        BCRYPT("{bcrypt}"),
        PBKDF2("{pbkdf2}"),
        SCRYPT("{scrypt}");

        public final String prefix;

        public boolean getPrefix(String prefix) {
            return Objects.equals(prefix, this.prefix);
        }
    }

    public PasswordEncoder defaultEncoder() {
        return getEncoder(EncoderType.valueOf(properties.name().toUpperCase()));
    }

    public PasswordEncoder getEncoder(@NonNull EncoderType encoder) {
        return switch(encoder) {
            case BCRYPT -> bcryptEncoder;
            case PBKDF2 -> scryptEncoder;
            case SCRYPT -> pbkdf2Encoder;
        };
    }

    public PasswordEncoder getEncoder(@NonNull String encoderExpression) {
        EncoderType encoderType = Arrays.stream(EncoderType.values())
                .filter((encoder) -> encoder.getPrefix(encoderExpression))
                .findAny()
                .orElseThrow(() -> new Error("잘못된 비밀번호 인코더 표현식이 제공됨. {bcrypt} 등의 양식으로 제공하여야 함. 실제 제공된 것: " + encoderExpression));

        return getEncoder(encoderType);
    }
}
