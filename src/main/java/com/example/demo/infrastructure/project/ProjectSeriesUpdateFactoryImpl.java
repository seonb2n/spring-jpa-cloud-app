package com.example.demo.infrastructure.project;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.service.ProjectReader;
import com.example.demo.domain.project.service.ProjectSeriesUpdateFactory;
import com.example.demo.domain.project.service.ProjectStore;
import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.project.task.action.Action;
import com.example.demo.domain.user.User;
import com.example.demo.interfaces.project.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectSeriesUpdateFactoryImpl implements ProjectSeriesUpdateFactory {
    private final ProjectStore projectStore;
    private final ProjectReader projectReader;

    @Override
    public Project updateProject(User user, ProjectCommand.UpdateProject updateProject) {
        Project project = projectReader.getProjectWithToken(updateProject.getProjectToken());
        project.updateProject(updateProject);
        updateProject.getUpdateTaskList().forEach(updateTask -> {
            if(updateTask.getTaskToken() == null) {
                Task initTask = updateTask.toEntity(project);
                projectStore.store(initTask);
            }
            else {
                updateTask(project, updateTask);
            }
        });
        projectStore.store(project);
        return project;
    }

    @Override
    public Task updateTask(Project project, ProjectCommand.UpdateTask updateTask) {
        Task task = projectReader.getTaskWithToken(updateTask.getTaskToken());
        task.updateTask(project, updateTask);
        //task 의 action 의 변경 내용에 대해서 update 가 필요하다.
        updateTask.getUpdateActionList().forEach(updateAction -> {
            if(updateAction.getActionToken() == null || updateAction.getActionToken().equals("")) {
                Action initAction = updateAction.toEntity(task);
                projectStore.store(initAction);
            }
            else {
                Action action = projectReader.getActionWithToken(updateAction.getActionToken());
                action.updateAction(updateAction);
                projectStore.store(action);
            }
        });
        projectStore.store(task);
        return task;
    }
}
