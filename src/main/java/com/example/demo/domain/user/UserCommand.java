package com.example.demo.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
User data input 을 처리하는 command
**/
public class UserCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterUser {
        private final String userEmail;
        private final String password;
        private final String userNickName;
        private final String startDayTime;
        private final String endDayTime;

        public User toEntity() {
            return User.builder()
                    .userEmail(userEmail)
                    .password(password)
                    .userNickName(userNickName)
                    .startDayTime(startDayTime)
                    .endDayTime(endDayTime)
                    .build();
        }
    }
}
