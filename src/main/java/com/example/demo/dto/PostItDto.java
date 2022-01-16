package com.example.demo.dto;

import com.example.demo.entity.PostItCategory;
import com.example.demo.entity.User;
import com.example.demo.support.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostItDto {

    private User user;
    private String content;
    private PostItCategory category;
    private Status status;

}
