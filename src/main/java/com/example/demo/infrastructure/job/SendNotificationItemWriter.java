package com.example.demo.infrastructure.job;

import com.example.demo.domain.notification.NotificationEntity;
import com.example.demo.infrastructure.notification.MessageSendService;
import com.example.demo.infrastructure.notification.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class SendNotificationItemWriter implements ItemWriter<NotificationEntity> {

    private final NotificationRepository notificationRepository;
    private final MessageSendService messageSendService;

    public SendNotificationItemWriter(NotificationRepository notificationRepository, MessageSendService messageSendService) {
        this.notificationRepository = notificationRepository;
        this.messageSendService = messageSendService;
    }

    @Override
    public void write(List<? extends NotificationEntity> notificationEntities) throws Exception {
        int count = 0;

        for (NotificationEntity entity : notificationEntities) {
            boolean successful = messageSendService.sendMessage(entity.getUserId(), entity.getText());

            if (successful) {
                entity.setSent(true);
                entity.setSentAt(LocalDateTime.now());
                notificationRepository.save(entity);
                count++;
            }
        }
        log.info("SendNotificationItemWrite : Task 마감 알림 {}/{} 건 전송 성공", count, notificationEntities.size());
    }
}
