package com.example.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationProperties(prefix = "app.jwt")
@ConfigurationPropertiesBinding
public record JwtProperties(
        String secret,
        Long expireIn
) {
    public JwtProperties {
        if (secret == null) secret = "lo7oultF+BsBSO6cMCae79s4qRZvJMxPiIM2v8QJ8R3yiFnzxNHSUy2w5pQDFwr7R1MdKfCs7wQPtNdXgXxJlA==";
        if (expireIn == null) expireIn = 1_800_000L;
    }
}
