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
    private final ProjectSeriesRegisterFactory projectSeriesRegisterFactory;
    private final ProjectSeriesUpdateFactory projectSeriesUpdateFactory;
    private final ProjectInfoMapper projectInfoMapper;

    @Override
    public String registerProject(ProjectCommand.RegisterProject registerProject) {
        // 1. command -> entity
        // 2. save entity
        // 3. entity -> info and return
        User user = userReader.getUserWithUserToken(registerProject.getUserToken());
        Project project = projectSeriesRegisterFactory.storeProject(user, registerProject);
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
        User user = userReader.getUserWithUserToken(updateProject.getUserToken());
        Project project = projectSeriesUpdateFactory.updateProject(user, updateProject);
        return project.getProjectToken();
    }

    @Override
    public String registerTask(ProjectCommand.RegisterTask registerTask) {
        // 1. command -> entity
        // 2. save entity
        // 3. entity -> info and return
        Project project = projectReader.getProjectWithToken(registerTask.getProjectToken());
        Task initTask = projectSeriesRegisterFactory.storeTask(project, registerTask);
        return initTask.getTaskToken();
    }

    @Override
    public String updateTask(ProjectCommand.UpdateTask updateTask) {
        Project project = projectReader.getProjectWithToken(updateTask.getProjectToken());
        Task task = projectSeriesUpdateFactory.updateTask(project, updateTask);
        return task.getTaskToken();
    }

    @Override
    public String registerAction(ProjectCommand.RegisterAction registerAction) {
        // 1. command -> entity
        // 2. save entity
        // 3. entity -> info and return
        Task task = projectReader.getTaskWithToken(registerAction.getTaskToken());
        Action initAction = projectSeriesRegisterFactory.storeAction(task, registerAction);
        return initAction.getActionToken();
    }

    @Override
    public ProjectInfo.Main retrieveProject(String projectToken) {
        Project project = projectReader.getProjectWithToken(projectToken);
        return projectInfoMapper.of(project, project.getTaskList());
    }
}
