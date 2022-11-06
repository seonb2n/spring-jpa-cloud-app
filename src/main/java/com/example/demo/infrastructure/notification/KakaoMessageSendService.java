package com.example.demo.infrastructure.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KakaoMessageSendService implements MessageSendService {

    @Override
    public boolean sendMessage(final Long userId, final String text) {
        log.info("KakaoMessage Sent user : {}, text : {}", userId, text);
        return true;
    }
}
