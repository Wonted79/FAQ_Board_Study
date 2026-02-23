package com.wonted79.faq_board.user.controller;

import com.wonted79.faq_board.user.dto.JoinRequest;
import com.wonted79.faq_board.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class JoinController {

    private final UserService userService;

    @GetMapping("/join")
    public String joinPage(Model model){
        JoinRequest joinRequest = new JoinRequest();
        joinRequest.setCompanyCode("samsung-sds");
        model.addAttribute("joinRequest",joinRequest);
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute JoinRequest joinRequest, Model model){
        //비밀번호 확인실패
        if(!joinRequest.getPassword().equals(joinRequest.getPasswordConfirm()))
            return "redirect:/auth/join";
        //이메일이 중복되었다면
        if(userService.existByEmail(joinRequest.getEmail())){
            return "redirect:/auth/join";
        }
        userService.join(joinRequest);
        return "redirect:/";
    }
}
