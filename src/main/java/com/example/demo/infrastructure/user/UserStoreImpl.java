package com.example.demo.infrastructure.user;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.service.UserStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserStoreImpl implements UserStore {

    private final UserRepository userRepository;

    @Override
    public User store(User initUser) {
        //TODO initUser 에 대한 유효성 검사 필요
        return userRepository.save(initUser);
    }
}
