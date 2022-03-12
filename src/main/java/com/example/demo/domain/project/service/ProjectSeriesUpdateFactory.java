package com.example.demo.domain.project.service;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.user.User;

public interface ProjectSeriesUpdateFactory {

    Project updateProject(User user, ProjectCommand.UpdateProject updateProject);

    Task updateTask(Project project, ProjectCommand.UpdateTask updateTask);

}
