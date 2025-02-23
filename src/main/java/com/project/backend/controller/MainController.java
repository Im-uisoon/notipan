package com.project.backend.controller;

import com.project.backend.Repository.PostRepository;
import com.project.backend.entity.PostEntity;
import org.springframework.ui.Model;
import com.project.backend.component.SessionManager;
import com.project.backend.entity.MemberEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        // DB에서 전체 게시물 수 계산
        long totalPostCount = postRepository.count();
        model.addAttribute("totalPostCount", totalPostCount);

        // 최근 게시물 3개
        List<PostEntity> recentPosts = postRepository.findTop3ByOrderByIdDesc();
        model.addAttribute("recentPosts", recentPosts);

        // 세션에서 로그인한 회원 정보 조회 (MemberEntity 타입으로 캐스팅)
        MemberEntity member = (MemberEntity) sessionManager.getSession(request);
        if (member == null) {
            // 로그인이 되어 있지 않으면 일반 홈 화면으로 이동
            return "index";
        }
        // 로그인 상태이면 회원 정보를 뷰에 담아 로그인한 홈 화면으로 이동
        model.addAttribute("member", member);
        return "index";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 세션 만료 처리
        sessionManager.expire(request);
        return "redirect:/";
    }
}
