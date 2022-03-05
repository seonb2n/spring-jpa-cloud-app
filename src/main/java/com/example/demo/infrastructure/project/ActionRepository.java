package com.example.demo.infrastructure.project;

import com.example.demo.domain.project.task.action.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Long> {

}
