package com.project.backend.service;

import com.project.backend.Repository.MemberRepository;
import com.project.backend.component.PasswordEncoder;
import com.project.backend.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 로그인 검증 (비밀번호가 일치하면 true 반환)
    public boolean checkLogin(String name, String password) {
        Optional<MemberEntity> optionalMember = memberRepository.findByName(name);
        if (optionalMember.isEmpty()) {
            return false; // 회원이 없으면 false
        }
        MemberEntity member = optionalMember.get();
        String storedPassword = member.getPassword();
        String storedSalt = member.getSalt();
        String inputHashed = passwordEncoder.encryptWithSalt(password, storedSalt);
        return storedPassword.equals(inputHashed);
    }

    // 이름으로 회원 조회
    public Optional<MemberEntity> findByName(String name) {
        return memberRepository.findByName(name);
    }
}
