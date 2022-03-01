package com.example.demo.domain.project.service;

import com.example.demo.domain.project.Project;

import java.util.List;

public interface ProjectStore {

    Project store(Project initProject);

    List<Project> storeAll(List<Project> initProjectList);
}
