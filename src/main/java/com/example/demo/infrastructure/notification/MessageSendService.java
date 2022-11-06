package com.example.demo.infrastructure.notification;

public interface MessageSendService {

    boolean sendMessage(final Long userId, final String text);

}
