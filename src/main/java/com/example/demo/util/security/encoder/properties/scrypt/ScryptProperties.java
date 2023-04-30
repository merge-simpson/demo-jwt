package com.example.demo.util.security.encoder.properties.scrypt;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationPropertiesBinding
public record ScryptProperties(
        Integer cpuCost,
        Integer memoryCost,
        Integer parallelism,
        Integer keyLength,
        Integer saltLength
) {
    public ScryptProperties {
        if (cpuCost == null) cpuCost = 65536;
        if (memoryCost == null) memoryCost = 8;
        if (parallelism == null) parallelism = 1;
        if (keyLength == null) keyLength = 32;
        if (saltLength == null) saltLength = 16;
    }
}
