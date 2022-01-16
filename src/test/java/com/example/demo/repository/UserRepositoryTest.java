package com.example.demo.repository;

import com.example.demo.dto.UserEnrollDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void userEnrollTest() throws Exception {
        //given
        String userEmail = "abc123@naver.com";

        UserEnrollDto userEnrollDto = UserEnrollDto.builder()
                .userEmail(userEmail)
                .userNickName("new-user-test")
                .password("1234")
                .startDayTime("09:00")
                .endDayTime("15:00")
                .build();

        //when
        userService.userEnrollService(userEnrollDto);

        //then
        User user = userRepository.findUserByUserEmail(userEmail).get();
        assertThat(user.getUserNickName()).isEqualTo("new-user-test");
    }
}