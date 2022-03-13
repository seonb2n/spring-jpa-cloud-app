package com.example.demo.domain.postIt;

import com.example.demo.domain.postIt.category.Category;
import com.example.demo.domain.postIt.category.service.CategoryReader;
import com.example.demo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

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
        private String categoryName; //String type 으로 받은 category 이름

        public PostIt toEntity(User user, Category category) {
            return PostIt.builder()
                    .user(user)
                    .userToken(userToken)
                    .content(content)
                    .status(status)
                    .category(category)
                    .categoryName(categoryName)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class UpdatePostIt {
        private String userToken;
        private List<UpdatePostItUnit> updatePostItUnitList;
    }

    @Getter
    @Builder
    @ToString
    public static class UpdatePostItUnit {
        private String content;
        private String status;
        private String categoryName;
        private String categoryToken;
        private String postItToken;

        public PostIt toEntity(User user, Category category) {
            return PostIt.builder()
                    .user(user)
                    .userToken(user.getUserToken())
                    .content(content)
                    .status(status)
                    .category(category)
                    .categoryName(categoryName)
                    .build();
        }
    }
}
