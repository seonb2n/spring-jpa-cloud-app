package com.example.demo.domain.notification;

import com.example.demo.domain.project.task.Task;

public class NotificationCommand {

    public static class createNotificationCommand {

        public static NotificationEntity toEntity(Task task, NotificationEvent event) {
            return NotificationEntity.builder()
                    .taskId(task.getTaskId())
                    .projectToken(task.getProjectToken())
                    .event(event)
                    .text(task.getTaskName())
                    .build();
        }
    }

}
