package com.project.backend.controller;

import com.project.backend.Repository.PostRepository;
import com.project.backend.component.SessionManager;
import com.project.backend.entity.MemberEntity;
import com.project.backend.entity.PostEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomemadeController {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/homemade")
    public String showPlacePage(HttpServletRequest request, Model model, @RequestParam(defaultValue = "0") int page) {
        // 세션 로그인 정보
        MemberEntity member = (MemberEntity) sessionManager.getSession(request);
        model.addAttribute("member", member);

        // 해당 게시글 불러오기 (최신순)
        Pageable pageable = PageRequest.of(page, 5); // 페이지 번호와 한 페이지에 5개씩 가져오기
        Page<PostEntity> postsPage = postRepository.findByBoardOrderByIdDesc("board2", pageable);

        model.addAttribute("posts", postsPage.getContent());
        model.addAttribute("totalPages", postsPage.getTotalPages()); // 전체 페이지 수
        model.addAttribute("currentPage", page); // 현재 페이지 번호

        return "homemade";
    }

    @GetMapping("/homemade/{id}")
    public String showPostDetail(@PathVariable("id") Long id, Model model) {
        //id로 게시글 조회
        Optional<PostEntity> postOpt = postRepository.findById(id);
        if (postOpt.isPresent()) {
            model.addAttribute("post", postOpt.get());
            return "postDetail";
        } else {
            return "redirect:/homemade";
        }
    }
}
