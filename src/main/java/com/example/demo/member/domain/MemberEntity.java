package com.example.demo.member.domain;

import com.example.demo.support.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = DataSourceConstants.TB_MEMBER
//        ,
//        schema = DataSourceConstants.DEFAULT_SCHEMA,
//        catalog = DataSourceConstants.DEFAULT_SCHEMA
)
public class MemberEntity extends BaseEntity {
    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String nickname;
}
