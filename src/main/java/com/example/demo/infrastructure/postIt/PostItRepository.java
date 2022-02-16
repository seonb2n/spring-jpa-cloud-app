package com.example.demo.infrastructure.postIt;

import com.example.demo.domain.postIt.PostIt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostItRepository extends JpaRepository<PostIt, Long> {

    Optional<PostIt> findPostItByPostItToken(String postItToken);

    List<PostIt> findAllByUserToken(String userToken);
}
