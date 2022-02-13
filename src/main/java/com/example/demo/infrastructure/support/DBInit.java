package com.example.demo.infrastructure.support;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 초기 테스트 데이터를 넣어주는 class
 */

@Component
@RequiredArgsConstructor
public class DBInit implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }
}
