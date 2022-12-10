package com.example.demo.domain.postIt.service;

import com.example.demo.domain.postIt.PostIt;
import com.example.demo.domain.postIt.PostItCommand;
import com.example.demo.domain.postIt.PostItInfo;

import java.util.List;

public interface PostItService {

    /**
     * postItList 를 받은 후, 새로운 포스트잇이면 등록하고 기존의 포스트잇을 수정한 경우는 변경사항을 반영한다.
     * @param updatePostIt
     * @return 사용자 고유 token
     */
    String updateAllPostIt(PostItCommand.UpdatePostIt updatePostIt);

    /**
     * 사용자 고유 token 을 사용해서 모든 postIt 을 반환한다.
     * @param userToken
     * @return postItInfo List
     */
    PostItInfo.PostItList getAllPostIt(String userToken);
}
