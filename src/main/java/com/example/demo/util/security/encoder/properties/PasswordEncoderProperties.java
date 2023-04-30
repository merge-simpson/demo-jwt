package com.example.demo.util.security.encoder.properties;

import com.example.demo.util.security.encoder.PasswordEncoderFactory.EncoderType;
import com.example.demo.util.security.encoder.properties.bcrypt.BcryptProperties;
import com.example.demo.util.security.encoder.properties.pbkdf2.Pbkdf2Properties;
import com.example.demo.util.security.encoder.properties.scrypt.ScryptProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "app.security.password")
@ConfigurationPropertiesBinding
public record PasswordEncoderProperties(
        EncoderType defaultEncoder,
        @NestedConfigurationProperty BcryptProperties bcrypt,
        @NestedConfigurationProperty ScryptProperties scrypt,
        @NestedConfigurationProperty Pbkdf2Properties pbkdf2
        ) {
    public PasswordEncoderProperties {
        if (defaultEncoder == null) defaultEncoder = EncoderType.BCRYPT;
    }
}
