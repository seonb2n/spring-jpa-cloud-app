package com.example.demo.domain.postIt.service;

import com.example.demo.domain.postIt.PostIt;
import com.example.demo.domain.postIt.PostItCommand;
import com.example.demo.domain.postIt.PostItInfo;
import com.example.demo.domain.postIt.category.Category;
import com.example.demo.domain.postIt.category.service.CategoryReader;
import com.example.demo.domain.postIt.category.service.CategoryStore;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.service.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostItServiceImpl implements PostItService{

    private final PostItReader postItReader;
    private final PostItStore postItStore;
    private final UserReader userReader;
    private final CategoryReader categoryReader;
    private final PostItSeriesUpdateFactory postItSeriesUpdateFactory;

    @Override
    public String updateAllPostIt(PostItCommand.UpdatePostIt updatePostIt) {
        User user = userReader.getUserWithUserToken(updatePostIt.getUserToken());
        postItSeriesUpdateFactory.updatePostItList(user, updatePostIt);
        return user.getUserToken();
    }
}
