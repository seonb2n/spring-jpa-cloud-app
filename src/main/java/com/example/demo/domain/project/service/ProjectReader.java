package com.example.demo.domain.project.service;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.project.task.action.Action;

import java.util.List;

public interface ProjectReader {

    Project getProjectWithToken(String projectToken);

    List<Project> getProjectList(String userToken);

    Task getTaskWithToken(String taskToken);

    Action getActionWithToken(String actionToken);
}
