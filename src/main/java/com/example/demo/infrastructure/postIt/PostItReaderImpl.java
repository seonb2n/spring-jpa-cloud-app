package com.example.demo.infrastructure.postIt;

import com.example.demo.common.exception.EntityNotFoundException;
import com.example.demo.domain.postIt.PostIt;
import com.example.demo.domain.postIt.service.PostItReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostItReaderImpl implements PostItReader {

    private final PostItRepository postItRepository;

    @Override
    public PostIt getPostIt(String postItToken) {
        return postItRepository.findPostItByPostItToken(postItToken)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<PostIt> getPostItList(String userToken) {
        return postItRepository.findAllByUserToken(userToken);
    }
}
