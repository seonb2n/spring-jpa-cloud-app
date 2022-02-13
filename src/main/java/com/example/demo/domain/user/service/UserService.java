package com.example.demo.domain.user.service;

import com.example.demo.domain.user.UserCommand;
import com.example.demo.domain.user.UserInfo;

public interface UserService {
    UserInfo.Main registerUser(UserCommand.RegisterUser registerUserCommand);
}
