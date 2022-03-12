package com.example.demo.domain.project.service;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.ProjectInfo;
import com.example.demo.domain.project.ProjectInfoMapper;
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
    private final ProjectInfoMapper projectInfoMapper;

    @Override
    public String registerProject(ProjectCommand.RegisterProject registerProject) {
        // 1. command -> entity
        // 2. save entity
        // 3. entity -> info and return
        User user = userReader.getUserWithUserToken(registerProject.getUserToken());
        Project project = projectSeriesFactory.storeProject(user, registerProject);
        return project.getProjectToken();
    }

    @Override
    public String registerNoneProject(String userToken) {
        User user = userReader.getUserWithUserToken(userToken);
        Project initProject = Project.builder()
                .projectName("none")
                .user(user)
                .userToken(userToken)
                .build();
        Project project = projectStore.store(initProject);
        return project.getProjectToken();
    }

    @Override
    public String updateProject(ProjectCommand.UpdateProject updateProject) {
        return null;
    }

    @Override
    public String registerTask(ProjectCommand.RegisterTask registerTask) {
        // 1. command -> entity
        // 2. save entity
        // 3. entity -> info and return
        Project project = projectReader.getProjectWithToken(registerTask.getProjectToken());
        Task initTask = projectSeriesFactory.storeTask(project, registerTask);
        return initTask.getTaskToken();
    }

    @Override
    public String updateTask(ProjectCommand.UpdateTask updateTask) {
        Task initTask = projectReader.getTaskWithToken(updateTask.getTaskToken());
        return null;
    }

    @Override
    public String registerAction(ProjectCommand.RegisterAction registerAction) {
        // 1. command -> entity
        // 2. save entity
        // 3. entity -> info and return
        Task task = projectReader.getTaskWithToken(registerAction.getTaskToken());
        Action initAction = projectSeriesFactory.storeAction(task, registerAction);
        return initAction.getActionToken();
    }

    @Override
    public ProjectInfo.Main retrieveProject(String projectToken) {
        Project project = projectReader.getProjectWithToken(projectToken);
        return projectInfoMapper.of(project, project.getTaskList());
    }
}
