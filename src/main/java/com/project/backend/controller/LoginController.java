package com.project.backend.controller;

import com.project.backend.component.SessionManager;
import com.project.backend.dto.LoginForm;
import com.project.backend.entity.MemberEntity;
import com.project.backend.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private SessionManager sessionManager;

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm,
                        BindingResult bindingResult,
                        HttpServletResponse response,
                        Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "입력값을 확인하세요.");
            return "login";
        }

        Optional<MemberEntity> optionalMember = memberService.findByName(loginForm.getName());

        if (optionalMember.isPresent() && memberService.checkLogin(loginForm.getName(), loginForm.getPassword())) {
            MemberEntity loginMember = optionalMember.get();
            sessionManager.createSession(loginMember, response);
            return "redirect:/";
        } else {
            model.addAttribute("error", "로그인에 실패했습니다.");
            return "login";
        }
    }
}
