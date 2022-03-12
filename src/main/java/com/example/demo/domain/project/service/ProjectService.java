package com.example.demo.domain.project.service;

import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.ProjectInfo;

public interface ProjectService {

    String registerProject(ProjectCommand.RegisterProject registerProject);

    String registerTask(ProjectCommand.RegisterTask registerTask);

    String registerAction(ProjectCommand.RegisterAction registerAction);

    ProjectInfo.Main retrieveProject(String projectToken);
}
