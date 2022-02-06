package com.example.demo.controller;

import com.example.demo.domain.dto.PostItDto;
import com.example.demo.application.service.PostItService;
import com.example.demo.infrastructure.support.Wrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/postIt")
@RequiredArgsConstructor
public class PostItController {

    private final PostItService postItService;

    @GetMapping(value = "/updatePostits", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostItDto> updatePostIts(@RequestBody Wrapper<PostItDto> data) {
        List<PostItDto> postItDtoList = data.getData();
        return postItService.updatePostItList(postItDtoList);
    }
}
