package com.example.demo.application.user;

import com.example.demo.domain.user.UserCommand;
import com.example.demo.domain.user.UserInfo;
import com.example.demo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;

    public UserInfo.Main registerUser(UserCommand.RegisterUser registerUser) {
        // 1.userService register
        var userInfo = userService.registerUser(registerUser);
        //TODO User 생성시에 null Project 를 하나 생성해서, 소속이 없는 Task 를 해당 project 에 추가해야 함
        return userInfo;
    }

    public UserInfo.Main getUser(String userToken) {
        var userInfo = userService.getUser(userToken);
        return userInfo;
    }

}
