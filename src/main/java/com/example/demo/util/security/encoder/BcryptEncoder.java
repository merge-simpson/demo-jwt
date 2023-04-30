package com.example.demo.util.security.encoder;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
public class BcryptEncoder extends BCryptPasswordEncoder {

    BcryptEncoder(int costFactor) {
        super(costFactor);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return "{bcrypt}" + super.encode(rawPassword);
    }
}
