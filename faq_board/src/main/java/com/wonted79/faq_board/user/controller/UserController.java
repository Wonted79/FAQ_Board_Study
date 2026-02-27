package com.wonted79.faq_board.user.controller;

import com.wonted79.faq_board.user.dto.SignUpRequest;
import com.wonted79.faq_board.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입 폼 전송
    @GetMapping("/signup")
    public String signUpForm(@ModelAttribute SignUpRequest signUpRequest, Model model){
        signUpRequest.setCompanyCode("samsung-sds");
        model.addAttribute("signUpRequest", signUpRequest);
        return "signUpForm";
    }

    //회원가입 로직 실행
    @PostMapping("/signup")
    public String signUp(@Valid @ModelAttribute SignUpRequest signUpRequest, Model model){
        //비밀번호 확인실패
        if(!signUpRequest.getPassword().equals(signUpRequest.getPasswordConfirm()))
            return "redirect:/auth/signup";
        //이메일이 중복되었다면
        if(userService.existByEmail(signUpRequest.getEmail())){
            return "redirect:/auth/signup";
        }
        userService.join(signUpRequest);
        return "redirect:/";
    }
}
