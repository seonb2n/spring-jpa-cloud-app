package com.example.demo.domain.user.service;

import com.example.demo.domain.user.User;

/**
User 정보를 서버에서 가져오는 Reader
**/
public interface UserReader {

    User getUserWithUserToken(String userToken);

    User getUserWithUserEmail(String userEmail);
}
