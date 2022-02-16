package com.example.demo.domain.postIt;

import com.example.demo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 PostIt data input 을 처리하는 command
 **/
public class PostItCommand {

    @Getter
    @Builder
    @ToString
    public static class ChangePostItStatus {
        private String postItToken;
        private String status;
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterPostIt {

        private String userToken;
        private String content;
        private String status; //String type 으로 complete, incomplete 받을 예정

        public PostIt toEntity(User user) {
            return PostIt.builder()
                    .user(user)
                    .userToken(userToken)
                    .content(content)
                    .status(status)
                    .build();
        }
    }

}
