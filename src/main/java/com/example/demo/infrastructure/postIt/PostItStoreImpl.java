package com.example.demo.infrastructure.postIt;

import com.example.demo.domain.postIt.PostIt;
import com.example.demo.domain.postIt.service.PostItStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class PostItStoreImpl implements PostItStore {

    private final PostItRepository postItRepository;

    @Override
    public PostIt store(PostIt initPostIt) {
        return postItRepository.save(initPostIt);
    }

    @Override
    public List<PostIt> storeAll(List<PostIt> postItList) {
        return postItRepository.saveAll(postItList);
    }
}
