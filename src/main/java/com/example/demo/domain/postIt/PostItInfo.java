package com.example.demo.domain.postIt;

import com.example.demo.domain.postIt.category.Category;
import com.example.demo.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 PostIt data output 을 처리하는 info
 **/

public class PostItInfo {

    @Getter
    @ToString
    public static class Main {
        private final Long postItId;
        private final String postItToken;
        private final String userToken;
        private final String content;
        private final PostIt.PostItStatus status;
        private final Category category;
        private final String categoryName;

        public Main(PostIt postIt) {
            this.postItId = postIt.getId();
            this.postItToken = postIt.getPostItToken();
            this.userToken = postIt.getUserToken();
            this.content = postIt.getContent();
            this.status = postIt.getStatus();
            this.category = postIt.getCategory();
            this.categoryName = postIt.getCategoryName();
        }
    }

    @Getter
    @ToString
    public static class PostItList {

        List<Main> postItInfoList;

        public PostItList(List<PostIt> postItList) {
            postItInfoList = new ArrayList<>();
            postItList.forEach(postIt -> postItInfoList.add(new Main(postIt)));
        }

    }


}
