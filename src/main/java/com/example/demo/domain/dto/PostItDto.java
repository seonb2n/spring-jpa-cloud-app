package com.example.demo.domain.dto;

import com.example.demo.domain.entity.PostIt;
import com.example.demo.infrastructure.support.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostItDto {

    private Long userId;
    private String content;
    private String category;
    private Status status;

    public PostItDto(PostIt postIt) {
        userId = postIt.getUser().getId();
        content = postIt.getContent();
        category = postIt.getCategory();
        status = postIt.getStatus();
    }
}
