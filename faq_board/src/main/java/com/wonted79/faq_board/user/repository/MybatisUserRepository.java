package com.wonted79.faq_board.user.repository;

import com.wonted79.faq_board.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisUserRepository implements UserRepository {

    private final UserMapper userMapper;

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public User findByUserId(Long userId) {
        return userMapper.findByUserId(userId);
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }
}
