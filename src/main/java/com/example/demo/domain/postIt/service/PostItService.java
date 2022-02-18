package com.example.demo.domain.postIt.service;

import com.example.demo.domain.postIt.PostItCommand;
import com.example.demo.domain.postIt.PostItInfo;

import java.util.List;

public interface PostItService {

    PostItInfo.Main registerPostIt(PostItCommand.RegisterPostIt registerPostIt);

    PostItInfo.Main changePostItStatus(PostItCommand.ChangePostItStatus changePostIt);

    PostItInfo.PostItList saveAllPostIt(List<PostItCommand.RegisterPostIt> registerPostItList);
}
