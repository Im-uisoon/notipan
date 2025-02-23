package com.project.backend.controller;

import com.project.backend.Repository.MemberRepository;
import com.project.backend.component.PasswordEncoder;
import com.project.backend.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public String JoinPage() {
        return "join";
    }

    @PostMapping("/join")
    public String JoinForm(@ModelAttribute MemberEntity member, Model model) {
        // 중복 아이디 검사
        if (memberRepository.findByName(member.getName()).isPresent()) {
            model.addAttribute("error", "해당 아이디를 이미 사용 중인 계정이 있습니다.");
            return "join";
        }


        // 비밀번호 해싱 및 솔트 생성
        String[] hashedPasswordAndSalt = passwordEncoder.encrypt(member.getPassword());
        member.setPassword(hashedPasswordAndSalt[0]);  // 해싱된 비밀번호
        member.setSalt(hashedPasswordAndSalt[1]);     // 솔트 저장

        memberRepository.save(member);

        return "redirect:/";
    }
}
