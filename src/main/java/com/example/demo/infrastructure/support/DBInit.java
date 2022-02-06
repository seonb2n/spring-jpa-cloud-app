package com.example.demo.infrastructure.support;

import com.example.demo.domain.entity.User;
import com.example.demo.infrastructure.repository.UserRepository;
import com.example.demo.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 초기 테스트 데이터를 넣어주는 class
 */

@Component
@RequiredArgsConstructor
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User defaultUser = userRepository.findUserById(1L).orElseGet(() -> {
                    User user = User.builder()
                            .userEmail("abc123@naver.com")
                            .password("1234")
                            .userNickName("test-steve")
                            .startDayTime("09:00")
                            .endDayTime("16:00")
                            .build();
                    userRepository.save(user);
                    userService.addAuthority(user.getId(), "ROLE_USER");
            return user;
        });

    }
}
