package com.example.demo.domain.project.service;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.project.task.action.Action;

import java.util.List;

public interface ProjectSeriesFactory {

    void storeProject(Project project, ProjectCommand.RegisterProject registerProject);

    Task storeTask(Project project, ProjectCommand.RegisterTask registerTask);

    Action storeAction(Task task, ProjectCommand.RegisterAction registerAction);
}
