package com.example.demo.domain.postIt.service;

import com.example.demo.domain.postIt.PostItCommand;
import com.example.demo.domain.postIt.PostItInfo;

import java.util.List;

public interface PostItService {

    String updateAllPostIt(PostItCommand.UpdatePostIt updatePostIt);

}
