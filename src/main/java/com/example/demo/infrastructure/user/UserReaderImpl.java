package com.example.demo.infrastructure.user;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.service.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {

    private final UserRepository userRepository;

    @Override
    public User getUser(String userToken) {
        return userRepository.findByUserToken(userToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}
