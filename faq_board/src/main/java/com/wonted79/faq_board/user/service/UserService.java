package com.wonted79.faq_board.user.service;

import com.wonted79.faq_board.user.dto.SignUpRequest;
import com.wonted79.faq_board.user.entity.User;
import com.wonted79.faq_board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //로그인
    public User login(String email, String password){
        User loginUser  = userRepository.findByEmail(email);
        log.info("loginUser : {}",loginUser);
        if (loginUser == null) return null;
        if (!loginUser.getPassword().equals(password)) return null;
        return loginUser;
    }

    public User findById(Long userId){
        return userRepository.findByUserId(userId);
    }

    //회원가입
    public User join(SignUpRequest signUpRequest){
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setNickname(signUpRequest.getNickname());
        user.setPassword(signUpRequest.getPassword());
        user.setCompanyCode(signUpRequest.getCompanyCode());

        userRepository.save(user);
        return null;
    }

    //이메일 중복체크
    public boolean existByEmail(String email){
        //이메일이 중복되었으면 false
        if(userRepository.findByEmail(email) != null) return true;
        return false;
    }
}
