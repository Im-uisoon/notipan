package com.project.backend.controller;

import com.project.backend.Repository.PostRepository;
import com.project.backend.component.SessionManager;
import com.project.backend.entity.MemberEntity;
import com.project.backend.entity.PostEntity;
import com.project.backend.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @Autowired
    private SessionManager sessionManager; //세션 확인

    @Autowired
    private MemberService memberService; //회원 정보 확인

    @Autowired
    private PostRepository postRepository; //게시물 저장

    @GetMapping("/post")
    public String showPostForm(HttpServletRequest request, Model model) {
        // 세션 정보 조회
        MemberEntity member = (MemberEntity) sessionManager.getSession(request);
        if (member == null) {
            return "redirect:/login";
        }

        //회원 이름, 글 저장 전 빈 객체
        model.addAttribute("member", member);
        model.addAttribute("post", new PostEntity());
        return "post";
    }

    @PostMapping("/post")
    public String submitPostForm(@ModelAttribute PostEntity post, HttpServletRequest request) {
        // 세션 로그인 정보
        MemberEntity member = (MemberEntity) sessionManager.getSession(request);
        if (member == null) {
            return "redirect:/login";
        }

        //작성자 이름 저장
        post.setAuthor(member.getName());
        //게시글 저장
        postRepository.save(post);

        return "redirect:/";
    }
}
