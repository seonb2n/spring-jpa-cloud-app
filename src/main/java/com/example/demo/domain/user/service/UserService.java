package com.example.demo.domain.user.service;

import com.example.demo.domain.user.UserCommand;
import com.example.demo.domain.user.UserInfo;

public interface UserService {

    /**
     * 사용자 등록하는 메서드
     * @param registerUserCommand
     * @return UserInfo.Main
     */
    UserInfo.Main registerUser(UserCommand.RegisterUser registerUserCommand);

    /**
     * 사용자 고유 token 을 바탕으로 사용자 정보를 가져오는 메서드
     * @param userToken
     * @return UserInfo.Main
     */
    UserInfo.Main getUser(String userToken);

    /**
     * 사용자 로그인하는 메서드
     * @param userEmail
     * @param userPassword
     * @return
     */
    UserInfo.Main loginUser(String userEmail, String userPassword);
}
