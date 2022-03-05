package com.example.demo.infrastructure.project;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.service.ProjectStore;
import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.project.task.action.Action;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectStoreImpl implements ProjectStore {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final ActionRepository actionRepository;

    @Override
    public Project store(Project initProject) {
        return projectRepository.save(initProject);
    }

    @Override
    public Task store(Task initTask) {
        return taskRepository.save(initTask);
    }

    @Override
    public Action store(Action initAction) {
        return actionRepository.save(initAction);
    }

    @Override
    public List<Project> storeAll(List<Project> initProjectList) {
        return projectRepository.saveAll(initProjectList);
    }
}
