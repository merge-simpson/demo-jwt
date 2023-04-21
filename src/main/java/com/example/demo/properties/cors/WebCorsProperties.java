package com.example.demo.properties.cors;

import com.example.demo.properties.cors.allowed.WebCorsAllowedProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "app.security.cors")
@ConfigurationPropertiesBinding
public record WebCorsProperties(
        @NestedConfigurationProperty WebCorsAllowedProperties allowed,
        String[] exposedHeaders
) {
}
