package com.example.demo.interfaces.user;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
api 통신에서 사용하는 UserDTO
**/
public class UserDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterRequest {

        @NotEmpty(message = "user Email 는 필수값입니다")
        private String userEmail;
        @NotEmpty(message = "password 는 필수값입니다")
        private String password;
        @NotEmpty(message = "userNickName 는 필수값입니다")
        private String userNickName;
        private String startDayTime;
        private String endDayTime;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterResponse {
        private final String userToken;
        private final String userEmail;
        private final String password;
        private final String userNickName;
        private final String startDayTime;
        private final String endDayTime;
        private final User.UserStatus status;

        public RegisterResponse(UserInfo.Main userInfo) {
            this.userToken = userInfo.getUserToken();
            this.userEmail = userInfo.getUserEmail();
            this.password = userInfo.getPassword();
            this.userNickName = userInfo.getUserNickName();
            this.startDayTime = userInfo.getStartDayTime();
            this.endDayTime = userInfo.getEndDayTime();
            this.status = userInfo.getStatus();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class GetWithTokenRequest{

        private String userToken;

    }

    @Getter
    @Setter
    @ToString
    public static class GetWithTokenResponse {

    }

}
