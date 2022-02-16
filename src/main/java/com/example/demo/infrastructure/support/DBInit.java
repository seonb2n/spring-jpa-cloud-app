package com.example.demo.infrastructure.support;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.service.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 초기 테스트 데이터를 넣어주는 class
 */

@Component
@RequiredArgsConstructor
public class DBInit implements CommandLineRunner {

    private final UserStore userStore;

    @Override
    public void run(String... args) throws Exception {
        User defaultUser = User.builder()
                    .userEmail("abc123@naver.com")
                    .password("1234")
                    .userNickName("test-steve")
                    .startDayTime("09:00")
                    .endDayTime("16:00")
                    .build();

        userStore.store(defaultUser);
    }
}
