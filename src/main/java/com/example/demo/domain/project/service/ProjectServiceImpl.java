package com.example.demo.domain.project.service;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.ProjectInfo;
import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.project.task.action.Action;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.service.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectReader projectReader;
    private final ProjectStore projectStore;
    private final UserReader userReader;
    private final ProjectSeriesFactory projectSeriesFactory;

    @Override
    public ProjectInfo.Main registerProject(ProjectCommand.RegisterProject registerProject) {
        // 1. command -> entity
        // 2. save entity
        // 3. entity -> info and return
        User user = userReader.getUserWithUserToken(registerProject.getUserToken());
        Project project = projectStore.store(registerProject.toEntity(user));
        projectSeriesFactory.storeProject(project, registerProject);
        return new ProjectInfo.Main(project);
    }

    @Override
    public ProjectInfo.TaskInfo registerTask(ProjectCommand.RegisterTask registerTask) {
        // 1. command -> entity
        // 2. save entity
        // 3. entity -> info and return
        Project project = projectReader.getProjectWithToken(registerTask.getProjectToken());
        Task initTask = projectSeriesFactory.storeTask(project, registerTask);
        return new ProjectInfo.TaskInfo(project, initTask);
    }

    @Override
    public ProjectInfo.ActionInfo registerAction(ProjectCommand.RegisterAction registerAction) {
        // 1. command -> entity
        // 2. save entity
        // 3. entity -> info and return
        Task task = projectReader.getTaskWithToken(registerAction.getTaskToken());
        Action initAction = projectSeriesFactory.storeAction(task, registerAction);
        return new ProjectInfo.ActionInfo(task, initAction);
    }
}
