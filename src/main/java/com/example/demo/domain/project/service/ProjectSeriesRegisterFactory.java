package com.example.demo.domain.project.service;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.project.task.action.Action;
import com.example.demo.domain.user.User;

public interface ProjectSeriesRegisterFactory {

    Project storeProject(User user, ProjectCommand.RegisterProject registerProject);

    Task storeTask(Project project, ProjectCommand.RegisterTask registerTask);

    Action storeAction(Task task, ProjectCommand.RegisterAction registerAction);
}
