package com.example.demo.util.security.encoder.properties.bcrypt;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationPropertiesBinding
public record BcryptProperties(
        Integer costFactor
) {
    public BcryptProperties {
        if (costFactor == null) costFactor = 10;
    }
}
