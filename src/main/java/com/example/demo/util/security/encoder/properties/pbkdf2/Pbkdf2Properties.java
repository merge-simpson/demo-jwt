package com.example.demo.util.security.encoder.properties.pbkdf2;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;

@ConfigurationPropertiesBinding
@Log4j2
public record Pbkdf2Properties(
        String secret,
        Integer saltLength,
        Integer costFactor,
        Pbkdf2WithHmacOf algorithm
) {
    public Pbkdf2Properties {
        if (secret == null) {
            log.warn("No secret key applied for PBKDF2 properties.");
            secret = "DEFAULT_KEY_COULD_BE_WELL_KNOWN_KEY_5low*sb&gfy(vga12B&^di45zi_uyr66lF9kT";
        }
        if (saltLength == null) saltLength = 16;
        if (costFactor == null) costFactor = 10;
        if (algorithm == null) algorithm = Pbkdf2WithHmacOf.SHA512;
    }

    public enum Pbkdf2WithHmacOf {
        SHA1, SHA256, SHA512
    }

    public SecretKeyFactoryAlgorithm secretKeyFactoryAlgorithm() {
        return switch (algorithm) {
            case SHA1 -> SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA1;
            case SHA256 -> SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256;
            case SHA512 -> SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512;
        };
    }
}
