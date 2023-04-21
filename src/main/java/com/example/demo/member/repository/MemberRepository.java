package com.example.demo.member.repository;

import com.example.demo.member.domain.MemberEntity;
import com.example.demo.member.repository.projection.AuthenticationProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

// 애노테이션으로 알리지 않아도 빈으로 등록됨.
public interface MemberRepository extends JpaRepository<MemberEntity, UUID> {
    Optional<AuthenticationProjection> findByEmail(String email);
}
