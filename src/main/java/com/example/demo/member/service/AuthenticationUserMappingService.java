package com.example.demo.member.service;

import com.example.demo.member.exception.AuthenticationErrorCode;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.repository.projection.AuthenticationProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class AuthenticationUserMappingService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AuthenticationProjection> optionalAuthentication = memberRepository.findByEmail(email);
        if (optionalAuthentication.isEmpty()) throw AuthenticationErrorCode.MEMBER_MISSING.defaultException();

        AuthenticationProjection authInfo = optionalAuthentication.get();

        return User
                .withUsername(email)
                .password(authInfo.password())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
