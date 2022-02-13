package com.example.demo.domain.user;

import lombok.Getter;
import lombok.ToString;

/**
User data output 을 처리하는 info
**/

public class UserInfo {

    @Getter
    @ToString
    public static class Main {
        private final Long userId;
        private final String userToken;
        private final String userEmail;
        private final String password;
        private final String userNickName;
        private final String startDayTime;
        private final String endDayTime;
        private final User.UserStatus status;

        public Main(User user) {
            this.userId = user.getId();
            this.userToken = user.getUserToken();
            this.userEmail = user.getUserEmail();
            this.password = user.getPassword();
            this.userNickName = user.getUserNickName();
            this.startDayTime = user.getStartDayTime();
            this.endDayTime = user.getEndDayTime();
            this.status = user.getStatus();
        }

    }

}
