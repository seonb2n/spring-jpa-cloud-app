package com.example.demo.application.postIt;

import com.example.demo.domain.postIt.PostItCommand;
import com.example.demo.domain.postIt.PostItInfo;
import com.example.demo.domain.postIt.service.PostItService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostItFacade {

    private final PostItService postItService;

    public PostItInfo.Main registerPostIt(PostItCommand.RegisterPostIt registerPostIt) {
        var postItInfo = postItService.registerPostIt(registerPostIt);
        return postItInfo;
    }

    public PostItInfo.PostItList registerAllPostIt(List<PostItCommand.RegisterPostIt> registerPostItList) {
        var postItInfoList = postItService.saveAllPostIt(registerPostItList);
        return postItInfoList;
    }
}
