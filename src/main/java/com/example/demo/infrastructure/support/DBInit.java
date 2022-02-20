package com.example.demo.infrastructure.support;

import com.example.demo.application.user.UserFacade;
import com.example.demo.domain.postIt.PostIt;
import com.example.demo.domain.postIt.category.Category;
import com.example.demo.domain.postIt.category.service.CategoryStore;
import com.example.demo.domain.postIt.service.PostItStore;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.service.UserReader;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.domain.user.service.UserStore;
import com.example.demo.infrastructure.postIt.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 초기 테스트 데이터를 넣어주는 class
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class DBInit implements CommandLineRunner {

    private final UserStore userStore;
    private final UserReader userReader;
    private final PostItStore postItStore;
    private final CategoryRepository categoryRepository;
    private final UserFacade userFacade;

    @Override
    public void run(String... args) throws Exception {
        User defaultUser = User.builder()
                .userEmail("abc123@naver.com")
                .password("1234")
                .userNickName("test-steve")
                .startDayTime("09:00")
                .endDayTime("16:00")
                .build();

        defaultUser.changeUserToken("user_1234");
        userStore.store(defaultUser);
        log.info(defaultUser.getUserToken());

        User user = userReader.getUserWithUserEmail("abc123@naver.com");
        String userToken = user.getUserToken();

        Category testCategory = Category.builder()
                .categoryName("test-category")
                .build();

        categoryRepository.save(testCategory);

        PostIt postIt1 = PostIt.builder()
                .user(user)
                .userToken(userToken)
                .content("test-postIt1")
                .status("INCOMPLETE")
                .category(testCategory)
                .build();

        PostIt postIt2 = PostIt.builder()
                .user(user)
                .userToken(userToken)
                .content("test-postIt2")
                .status("INCOMPLETE")
                .category(testCategory)
                .build();


        List<PostIt> postItList = new ArrayList<>();
        postItList.add(postIt1);
        postItList.add(postIt2);

        postItStore.storeAll(postItList);
    }
}
