package com.example.demo.infrastructure.project;

import com.example.demo.domain.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectByProjectToken(String projectToken);

    List<Project> findAllByUserToken(String userToken);
}
