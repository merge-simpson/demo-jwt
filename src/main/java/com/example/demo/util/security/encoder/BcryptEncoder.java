package com.example.demo.util.security.encoder;

import com.example.demo.properties.PasswordEncoderProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptEncoder extends BCryptPasswordEncoder {

    BcryptEncoder(PasswordEncoderProperties properties) {
        super(properties.costFactor());
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return "{bcrypt}" + super.encode(rawPassword);
    }
}
