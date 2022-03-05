package com.example.demo.infrastructure.project;

import com.example.demo.common.exception.EntityNotFoundException;
import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.service.ProjectReader;
import com.example.demo.domain.project.task.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProjectReaderImpl implements ProjectReader {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Override
    public Project getProjectWithToken(String projectToken) {
        return projectRepository.findProjectByProjectToken(projectToken)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Project> getProjectList(String userToken) {
        return projectRepository.findAllByUserToken(userToken);
    }

    @Override
    public Task getTaskWithToken(String taskToken) {
        return taskRepository.findTaskByTaskToken(taskToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}
