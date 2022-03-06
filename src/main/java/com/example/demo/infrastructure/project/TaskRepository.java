package com.example.demo.infrastructure.project;

import com.example.demo.domain.project.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findTaskByTaskToken(String taskToken);
}
