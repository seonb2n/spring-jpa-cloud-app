package com.example.demo.dto;

import com.example.demo.entity.PostIt;
import com.example.demo.support.Status;
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
