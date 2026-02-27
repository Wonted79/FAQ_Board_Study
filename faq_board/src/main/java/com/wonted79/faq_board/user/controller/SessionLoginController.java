package com.wonted79.faq_board.user.controller;

import com.wonted79.faq_board.user.dto.LoginRequest;
import com.wonted79.faq_board.user.entity.User;
import com.wonted79.faq_board.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SessionLoginController {

    private final UserService userService;

    //로그인폼 전송
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    //로그인 로직 , 세션 생성
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest, HttpServletRequest httpRequest) {
        User loginUser = userService.login(loginRequest.getEmail(),loginRequest.getPassword());
        log.info("loginUser : {}",loginUser);
        if (loginUser ==null) {
            return "redirect:/auth/login";
        }
        HttpSession session = httpRequest.getSession();
        session.setAttribute("userId",loginUser.getUserId());

        return "redirect:/auth/";
    }

    //로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate(); // 세션 삭제
        }

        return "redirect:/auth/login";
    }

    //홈 화면 ,사용자 닉네임 표시
    @GetMapping("/")
    public String home(@SessionAttribute(name = "userId", required = false) Long userId,
                       Model model) {
        log.info("userId : {}",userId);
        if (userId != null) {
            User user = userService.findById(userId);
            log.info("user nickname: {}",user.getNickname());
            model.addAttribute("nickname", user.getNickname());
        }

        return "home";
    }
}
