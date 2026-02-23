package com.wonted79.faq_board.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRequest {
    private String companyCode;
    private String nickname;
    private String password;
    private String passwordConfirm;
    private String email;
}
