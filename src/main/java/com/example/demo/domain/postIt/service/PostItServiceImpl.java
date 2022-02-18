package com.example.demo.domain.postIt.service;

import com.example.demo.domain.postIt.PostIt;
import com.example.demo.domain.postIt.PostItCommand;
import com.example.demo.domain.postIt.PostItInfo;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.service.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostItServiceImpl implements PostItService{

    private final PostItReader postItReader;
    private final PostItStore postItStore;
    private final UserReader userReader;

    @Override
    @Transactional
    public PostItInfo.Main registerPostIt(PostItCommand.RegisterPostIt registerPostIt) {
        // 1.command -> initPostIt
        // 2. save initPostIt to DB
        // 3. PostIt -> PostItInfo and return
        var initPostIt = registerPostIt.toEntity(userReader.getUserWithUserToken(registerPostIt.getUserToken()));
        PostIt postIt = postItStore.store(initPostIt);
        return new PostItInfo.Main(postIt);
    }

    @Override
    @Transactional
    public PostItInfo.Main changePostItStatus(PostItCommand.ChangePostItStatus changePostIt) {
        // 1. command -> PostIt
        // 2. PostIt Entity.change Status
        // 2-1. save PostIt(영속성 컨텍스트에 의해 자동 save 처리)
        // 3. PostIt -> PostItInfo and return
        PostIt postIt = postItReader.getPostIt(changePostIt.getPostItToken());
        postIt.changePostItStatus(changePostIt.getStatus());
        return new PostItInfo.Main(postIt);
    }

    @Override
    public PostItInfo.PostItList saveAllPostIt(List<PostItCommand.RegisterPostIt> registerPostItList) {

        //TODO 로그인한 세션 정보를 바탕으로 유저 정보를 가져와야 함

        // 0. user 정보를 가져옴
        // 1. command -> postIt
        // 2. save PostIt to DB
        User user = userReader.getUserWithUserToken(registerPostItList.get(0).getUserToken());
        List<PostIt> initPostItList = registerPostItList.stream().map(postItCommand -> postItCommand.toEntity(user)).collect(Collectors.toList());
        var postItList = postItStore.storeAll(initPostItList);
        return new PostItInfo.PostItList(postItList);
    }
}
