package com.project.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저 아이디
    @Column(nullable = false, length = 30)
    private String name;

    // 유저 비밀번호
    @Column(nullable = false)
    private String password;

    // 솔트 필드 추가
    private String salt;
}
