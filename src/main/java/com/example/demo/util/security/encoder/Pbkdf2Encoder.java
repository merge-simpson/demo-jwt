package com.example.demo.util.security.encoder;

import com.example.demo.properties.PasswordEncoderProperties;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Pbkdf2Encoder extends Pbkdf2PasswordEncoder {

    Pbkdf2Encoder(PasswordEncoderProperties properties) {
        super("key-unloaded", 20, properties.costFactor(), SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512);
    }
}
