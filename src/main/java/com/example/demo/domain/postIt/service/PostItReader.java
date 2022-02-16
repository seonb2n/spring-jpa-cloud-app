package com.example.demo.domain.postIt.service;

import com.example.demo.domain.postIt.PostIt;

import java.util.List;

/**
 PostIt 정보를 서버에서 가져오는 Reader
 **/
public interface PostItReader {

    PostIt getPostIt(String postItToken);

    List<PostIt> getPostItList(String userToken);
}
