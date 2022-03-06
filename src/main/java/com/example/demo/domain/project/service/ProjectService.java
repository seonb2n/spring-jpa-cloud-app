package com.example.demo.domain.project.service;

import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.ProjectInfo;

public interface ProjectService {

    ProjectInfo.Main registerProject(ProjectCommand.RegisterProject registerProject);

    ProjectInfo.TaskInfo registerTask(ProjectCommand.RegisterTask registerTask);

    ProjectInfo.ActionInfo registerAction(ProjectCommand.RegisterAction registerAction);

    ProjectInfo.Main retrieveProject(String projectToken);
}
