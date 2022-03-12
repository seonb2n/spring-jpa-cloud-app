package com.example.demo.domain.project.service;

import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.ProjectInfo;
import com.example.demo.domain.user.User;

public interface ProjectService {

    String registerProject(ProjectCommand.RegisterProject registerProject);

    String registerNoneProject(String userToken);

    String updateProject(ProjectCommand.UpdateProject updateProject);

    String registerTask(ProjectCommand.RegisterTask registerTask);

    String updateTask(ProjectCommand.UpdateTask updateTask);

    String registerAction(ProjectCommand.RegisterAction registerAction);

    ProjectInfo.Main retrieveProject(String projectToken);}
