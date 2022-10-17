package com.example.demo.infrastructure.log;

import com.example.demo.domain.log.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<UserLog, Long> {
}
