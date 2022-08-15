package com.example.demo.application.user;

import com.example.demo.domain.project.service.ProjectService;
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
    private final ProjectService projectService;

    public UserInfo.Main registerUser(UserCommand.RegisterUser registerUser) {
        // 1.userService register
        var userInfo = userService.registerUser(registerUser);
        projectService.registerNoneProject(userInfo.getUserToken());
        return userInfo;
    }

    public UserInfo.Main getUser(String userToken) {
        var userInfo = userService.getUser(userToken);
        return userInfo;
    }

    public UserInfo.Main login(String userEmail, String password) {
        return userService.loginUser(userEmail, password);
    }
}
