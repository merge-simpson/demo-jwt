package com.example.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationProperties(prefix = "app.security.encoder")
@ConfigurationPropertiesBinding
public record PasswordEncoderProperties(
        String name,
        Integer costFactor
) {
    public PasswordEncoderProperties {
        if (name == null) name = "bcrypt";
        if (costFactor == null) costFactor = 10;
    }
}
