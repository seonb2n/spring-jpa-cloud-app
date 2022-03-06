package com.example.demo.domain.project.service;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.project.task.action.Action;

import java.util.List;

public interface ProjectStore {

    Project store(Project initProject);

    Task store(Task initTask);

    Action store(Action initAction);

    List<Project> storeAll(List<Project> initProjectList);
}
