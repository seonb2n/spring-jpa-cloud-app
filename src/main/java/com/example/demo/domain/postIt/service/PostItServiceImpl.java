package com.example.demo.domain.postIt.service;

import com.example.demo.domain.postIt.PostIt;
import com.example.demo.domain.postIt.PostItCommand;
import com.example.demo.domain.postIt.PostItInfo;
import com.example.demo.domain.user.service.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
