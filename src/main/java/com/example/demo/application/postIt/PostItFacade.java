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

    public String updateAllPostIt(PostItCommand.UpdatePostIt postItUpdateCommand) {
        return postItService.updateAllPostIt(postItUpdateCommand);
    }

    public PostItInfo.PostItList getAllPostIt(String userToken) {
        return postItService.getAllPostIt(userToken);
    }
}
