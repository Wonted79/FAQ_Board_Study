package com.wonted79.faq_board.user.repository;

import com.wonted79.faq_board.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByEmail(String email);
    User findByUserId(Long userId);
    void save(User user);
}
