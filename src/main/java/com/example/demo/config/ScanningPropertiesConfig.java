package com.example.demo.config;

import com.example.demo.support.Constants;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(basePackages = Constants.BASE_PACKAGE)
public class ScanningPropertiesConfig {
}
