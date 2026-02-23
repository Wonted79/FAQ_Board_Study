package com.wonted79.faq_board.user.repository;

import com.wonted79.faq_board.user.entity.User;

public interface UserRepository {
    User findByEmail(String email);
    User findByUserId(Long userId);
    void save(User user);
}
