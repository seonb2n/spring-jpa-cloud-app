package com.example.demo.repository;

import com.example.demo.dto.PostItDto;
import com.example.demo.entity.PostIt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostItRepository extends JpaRepository<PostIt, Long> {

    //포스트잇 조회
    @Query("select new com.example.demo.dto.PostItDto(p.user.id, p.content, p.category, p.status) " +
            "from PostIt p " +
            "join p.user u")
    List<PostItDto> findPostItDto();

    //포스트잇 수정

    //포스트잇 추가

}
