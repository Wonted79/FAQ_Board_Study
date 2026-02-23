package com.wonted79.faq_board.user.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {
    private Long userId;
    private String password;
    private String email;
    private Role role;
    private String companyCode;
    private LocalDateTime joinedAt;
    private String nickname;
    private boolean isDeleted;
    private LocalDateTime deletedAt;

}
