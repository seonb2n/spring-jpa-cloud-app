package com.example.demo.service;

import com.example.demo.dto.PostItDto;
import com.example.demo.entity.PostIt;
import com.example.demo.repository.PostItRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostItService {

    private final PostItRepository postItRepository;
    private final UserRepository userRepository;

    //post it 단일 개체를 변경
    public PostIt updatePostIt(PostItDto postItDto) {
        PostIt postIt = PostIt.builder()
                .user(userRepository.findUserById(postItDto.getUserId()).get())
                .category(postItDto.getCategory())
                .content(postItDto.getContent())
                .status(postItDto.getStatus())
                .build();

        return postItRepository.save(postIt);
    }

    //postit 여러 개체를 변경
    //자료를 주고 받는 것은 Dto 로
    public List<PostItDto> updatePostItList(List<PostItDto> postItDtoList) {
        List<PostIt> postItList = new ArrayList<>();
        for (PostItDto postItDto : postItDtoList) {
            PostIt postIt = PostIt.builder()
                    .user(userRepository.findUserById(postItDto.getUserId()).get())
                    .category(postItDto.getCategory())
                    .content(postItDto.getContent())
                    .status(postItDto.getStatus())
                    .build();
            postItList.add(postIt);
        }

        List<PostIt> postIts = postItRepository.saveAll(postItList);
        return postIts.stream().map(p -> new PostItDto(p)).collect(Collectors.toList());
    }
}
