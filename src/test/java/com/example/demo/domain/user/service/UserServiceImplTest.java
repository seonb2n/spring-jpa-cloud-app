package com.example.demo.domain.user.service;

import com.example.demo.common.exception.UserLoginFailException;
import com.example.demo.common.util.log.LogService;
import com.example.demo.domain.log.UserLog;
import com.example.demo.domain.user.User;
import com.example.demo.infrastructure.log.LogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserStore userStore;

    @Mock
    private UserReader userReader;

    @Autowired
    private LogRepository logRepository;

    private LogService logService;
    private UserService userService;

    @BeforeEach
    public void beforeEach() throws Exception {
        logService = new LogService(logRepository);
        userService = new UserServiceImpl(userStore, userReader, logService);
    }

    @DisplayName("사용자 로그인이 실패하면 Log가 기록된다.")
    @Test
    public void givenUserNameAndPassword_whenTryUserLogIn_thenSaveLog() throws Exception {
        //given
        User user = new User("test@naver.com", "1234", "testNick", "null", "null");

        given(userReader.getUserWithUserEmail(any())).willReturn(user);

        //when & then
        String userEmail = "test@naver.com";
        String userPasword= "123";
        assertThrows(UserLoginFailException.class, () -> userService.loginUser(userEmail, userPasword));

    }


}