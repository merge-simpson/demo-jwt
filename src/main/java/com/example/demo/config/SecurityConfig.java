package com.example.demo.config;

import com.example.demo.util.security.encoder.PasswordEncoderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoderFactory passwordEncoderFactory;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                // No Session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests()
                .anyRequest().permitAll();

        return http.build();
    }

    // Authentication Manager를 사용하면
    // Password Encoder -> 회원가입, 비밀번호 변경 등에서 비밀번호를 '생성' 할 때 사용.
    // Authentication Manager -> 로그인 등에서 사용. 인증 정보가 일치하는지 확인.
    // (단, 데이터 이관 등에서 발생하는 패스워드 인코더 불일치를 Authentication Manager가 완전히 해결해 주지는 못하므로 Password Encoder 팩토리 예시를 작성함.)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return passwordEncoderFactory.defaultEncoder();
    }
}
