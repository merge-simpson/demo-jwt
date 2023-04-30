package com.example.demo.util.security.encoder;

import com.example.demo.util.security.encoder.properties.PasswordEncoderProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public final class PasswordEncoderFactory {
    private final PasswordEncoderProperties properties;

    public enum EncoderType {
        BCRYPT("{bcrypt}"),
        PBKDF2("{pbkdf2}"),
        SCRYPT("{scrypt}");

        public final String PREFIX;

        EncoderType(String prefix) {
            assert Arrays.stream(values())
                    .noneMatch(item -> Objects.equals(item.PREFIX, prefix))
                    : "인코더 유형의 Prefix는 중복이 없도록 작성.";

            PREFIX = prefix;
        }

        public static EncoderType findByPrefixExpression(String expressionWithBrackets) {
            return Arrays.stream(values())
                    .filter((encoderType) -> Objects.equals(encoderType.PREFIX, expressionWithBrackets))
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException(
                            MessageFormat.format(
                                    "잘못된 비밀번호 인코더 표현식이 제공됨. {bcrypt} 등의 양식으로 제공하여야 함. 제공된 내용은: {0}, 허용된 목록: {1}",
                                    expressionWithBrackets,
                                    Arrays.stream(values()).map(item -> item.PREFIX)
                            )
                    ));
        }
    }

    public PasswordEncoder defaultEncoder() {
        return getEncoder(properties.defaultEncoder());
    }

    public PasswordEncoder getEncoder(@NonNull EncoderType encoder) {
        return switch(encoder) {
            case BCRYPT -> createBcrypt();
            case SCRYPT -> createScrypt();
            case PBKDF2 -> createPbkdf2();
        };
    }

    public PasswordEncoder getEncoderWithBracketExpression(@NonNull String encoderExpression) {
        EncoderType encoderType = EncoderType.findByPrefixExpression(encoderExpression);
        return getEncoder(encoderType);
    }

    private BCryptPasswordEncoder createBcrypt() {
        int costFactor = properties.bcrypt().costFactor();
        return new BcryptEncoder(costFactor);
    }

    private SCryptPasswordEncoder createScrypt() {
        int cpuCost = properties.scrypt().cpuCost();
        int memoryCost = properties.scrypt().memoryCost();
        int parallelism = properties.scrypt().parallelism();
        int keyLength = properties.scrypt().keyLength();
        int saltLength = properties.scrypt().saltLength();

        return new SCryptPasswordEncoder(
                cpuCost,
                memoryCost,
                parallelism,
                keyLength,
                saltLength);
    }

    private Pbkdf2PasswordEncoder createPbkdf2() {
        String secretKey = properties.pbkdf2().secret();
        Integer saltLength = properties.pbkdf2().saltLength();
        Integer costFactor = properties.pbkdf2().costFactor();
        SecretKeyFactoryAlgorithm algorithm = properties.pbkdf2().secretKeyFactoryAlgorithm();

        return new Pbkdf2PasswordEncoder(
                secretKey,
                saltLength,
                costFactor,
                algorithm);
    }
}
