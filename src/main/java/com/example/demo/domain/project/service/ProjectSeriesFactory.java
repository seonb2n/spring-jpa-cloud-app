package com.example.demo.domain.project.service;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.task.Task;

import java.util.List;

public interface ProjectSeriesFactory {

    List<Task> store(Project project, ProjectCommand.RegisterProject registerProject);
}
