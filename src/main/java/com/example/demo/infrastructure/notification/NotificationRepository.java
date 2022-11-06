package com.example.demo.infrastructure.notification;

import com.example.demo.domain.notification.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
