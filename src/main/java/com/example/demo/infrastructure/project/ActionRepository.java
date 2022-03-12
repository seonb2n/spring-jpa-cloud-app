package com.example.demo.infrastructure.project;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.task.action.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActionRepository extends JpaRepository<Action, Long> {
    Optional<Action> findActionByActionToken (String actionToken);

}
