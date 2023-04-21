package com.example.demo.util.security.jwt;

import com.example.demo.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key secretKey;
    private final Long expireIn;


    public JwtTokenProvider(JwtProperties jwtProperties) {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.secret());
        secretKey = Keys.hmacShaKeyFor(keyBytes);
        expireIn = jwtProperties.expireIn();
    }

    public String generate(String email) {
        return generate(email, "USER");
    }

    public String generate(String email, String... roles) {
        Claims claims = Jwts.claims().setSubject(email);

        Date now = new Date();
        Date expireAt = new Date(now.getTime() + expireIn);

        claims.put("roles", roles);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireAt)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }
}
