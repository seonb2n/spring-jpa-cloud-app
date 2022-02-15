package com.example.demo.domain.postIt;

import com.example.demo.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 PostIt data output 을 처리하는 info
 **/

public class PostItInfo {

    @Getter
    @ToString
    public static class Main {
        private final Long postItId;
        private final String postItToken;
        private final User user;
        private final String userToken;
        private final String content;
        private final PostIt.PostItStatus status;

        public Main(PostIt postIt) {
            this.postItId = postIt.getId();
            this.postItToken = postIt.getPostItToken();
            this.user = postIt.getUser();
            this.userToken = postIt.getUserToken();
            this.content = postIt.getContent();
            this.status = postIt.getStatus();
        }
    }


}
