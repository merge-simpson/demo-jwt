package com.example.demo.properties.cors.allowed;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@ConfigurationPropertiesBinding
public record WebCorsAllowedProperties(
        String[] headers,
        String[] methods,
        String[] origins
) {
    public WebCorsAllowedProperties {
        if (origins == null || origins.length == 0) {
            origins = new String[] {"http://localhost:3000"};
        }
        if (headers == null || headers.length == 0) headers = new String[] {"*"};
        if (methods == null || methods.length == 0) headers = new String[] {"*"};
    }
}
