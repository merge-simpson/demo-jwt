package com.example.demo.config;

import com.example.demo.properties.cors.WebCorsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    protected final WebCorsProperties corsProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(corsProperties.allowed().origins())
                .allowedMethods(corsProperties.allowed().methods())
                .allowedHeaders(corsProperties.allowed().headers())
                .allowCredentials(true) // for "Access-Control-Allow-Credentials"
                .exposedHeaders(corsProperties.exposedHeaders())
                // .allowCredentials(true)
                .maxAge(3600L); // seconds
    }
}
