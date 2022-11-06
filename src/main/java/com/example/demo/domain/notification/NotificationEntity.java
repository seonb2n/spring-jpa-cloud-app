package com.example.demo.domain.notification;

import com.example.demo.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "notification")
@NoArgsConstructor
public class NotificationEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationSeq;
    private Long taskId;
    private Long userId;

    @Enumerated(EnumType.STRING)
    private NotificationEvent event;
    private String text;
    private boolean sent;
    private LocalDateTime sentAt;

    @Builder
    public NotificationEntity(Long taskId, Long userId, NotificationEvent event, String text) {
        this.taskId = taskId;
        this.userId = userId;
        this.event = event;
        this.text = text;
        this.sent = false;
    }
}

