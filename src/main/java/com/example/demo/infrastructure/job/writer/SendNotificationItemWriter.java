package com.example.demo.infrastructure.job.writer;

import com.example.demo.domain.notification.NotificationEntity;
import com.example.demo.domain.project.Project;
import com.example.demo.infrastructure.notification.MessageSendService;
import com.example.demo.infrastructure.notification.NotificationRepository;
import com.example.demo.infrastructure.project.ProjectRepository;
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
    private final ProjectRepository projectRepository;

    public SendNotificationItemWriter(NotificationRepository notificationRepository, MessageSendService messageSendService, ProjectRepository projectRepository) {
        this.notificationRepository = notificationRepository;
        this.messageSendService = messageSendService;
        this.projectRepository = projectRepository;
    }

    @Override
    public void write(List<? extends NotificationEntity> notificationEntities) throws Exception {
        int count = 0;

        for (NotificationEntity entity : notificationEntities) {
            Project project = projectRepository.findProjectByProjectToken(entity.getProjectToken()).orElseThrow();
            boolean successful = messageSendService.sendMessage(project.getUser().getId(), entity.getText());

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
