package com.example.demo.domain.postIt.service;

import com.example.demo.domain.postIt.PostIt;

import java.util.List;

/**
 PostIt 정보를 서버에서 가져오는 Reader
 **/
public interface PostItReader {

    /**
     * postIt 고유 token 을 사용해서 postIt 객체를 가져온다.
     * @param postItToken
     * @return postIt
     */
    PostIt getPostIt(String postItToken);

    /**
     * 사용자 고유 token 을 사용해서 postItList 를 가져온다.
     * @param userToken
     * @return postIt
     */
    List<PostIt> getPostItList(String userToken);
}
