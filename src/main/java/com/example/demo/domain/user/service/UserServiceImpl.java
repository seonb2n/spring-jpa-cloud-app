package com.example.demo.domain.user.service;

import com.example.demo.common.exception.UserLoginFailException;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserCommand;
import com.example.demo.domain.user.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserStore userStore;
    private final UserReader userReader;

    @Override
    @Transactional
    public UserInfo.Main registerUser(UserCommand.RegisterUser registerUserCommand) {
        // 1.command -> initUser
        // 2. save initUser to DB
        // 3. User -> UserInfo and return
        var initUser = registerUserCommand.toEntity();
        User user = userStore.store(initUser);
        return new UserInfo.Main(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfo.Main getUser(String userToken) {
        User user = userReader.getUserWithUserToken(userToken);
        return new UserInfo.Main(user);
    }

    @Override
    public UserInfo.Main loginUser(String userEmail, String userPassword) {
        User user = userReader.getUserWithUserEmail(userEmail);
        if (user.getPassword().equals(userPassword)) {
            return new UserInfo.Main(user);
        }
        throw new UserLoginFailException();
    }
}
