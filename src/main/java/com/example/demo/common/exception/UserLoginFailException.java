package com.example.demo.common.exception;


import com.example.demo.common.response.ErrorCode;

public class UserLoginFailException extends BaseException {

    public UserLoginFailException() {
        super(ErrorCode.USER_LOGIN_FAIL);
    }

    public UserLoginFailException(String message) {
        super(message, ErrorCode.USER_LOGIN_FAIL);
    }
}
