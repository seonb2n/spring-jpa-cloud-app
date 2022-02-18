package com.example.demo.domain.postIt.service;

import com.example.demo.domain.postIt.PostIt;

import java.util.List;

public interface PostItStore {

    PostIt store(PostIt initPostIt);

    List<PostIt> storeAll(List<PostIt> postItList);
}
