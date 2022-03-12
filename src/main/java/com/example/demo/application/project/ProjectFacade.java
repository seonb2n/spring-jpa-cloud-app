package com.example.demo.application.project;

import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.ProjectInfo;
import com.example.demo.domain.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectFacade {

    private final ProjectService projectService;

    public String registerProject(ProjectCommand.RegisterProject registerProject) {
        return projectService.registerProject(registerProject);
    }

    public String registerTask(ProjectCommand.RegisterTask registerTask) {
         return projectService.registerTask(registerTask);
    }

    public String updateTask(ProjectCommand.UpdateTask updateTask) {
        return projectService.updateTask(updateTask);

    }

    public String registerAction(ProjectCommand.RegisterAction registerAction) {
        return projectService.registerAction(registerAction);
    }

    public ProjectInfo.Main retrieveProject(String projectToken) {
        return projectService.retrieveProject(projectToken);
    }
}
