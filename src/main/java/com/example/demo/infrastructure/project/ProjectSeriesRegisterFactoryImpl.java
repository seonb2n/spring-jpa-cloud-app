package com.example.demo.infrastructure.project;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.service.ProjectReader;
import com.example.demo.domain.project.service.ProjectSeriesRegisterFactory;
import com.example.demo.domain.project.service.ProjectStore;
import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.project.task.action.Action;
import com.example.demo.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectSeriesRegisterFactoryImpl implements ProjectSeriesRegisterFactory {
    private final ProjectReader projectReader;
    private final ProjectStore projectStore;

    @Override
    public Project storeProject(User user, ProjectCommand.RegisterProject registerProject) {
       //프로젝트 하위의 task 와 action 에 대해서 entity 화 해서 저장
        Project initProject = projectStore.store(registerProject.toEntity(user));
        registerProject.getRegisterTaskList().forEach(registerTask ->
            storeTask(initProject, registerTask)
        );
        return projectReader.getProjectWithToken(initProject.getProjectToken());
    }

    //task 와 하위에 있는 action 까지 모두 저장할 때 사용
    @Override
    public Task storeTask(Project project, ProjectCommand.RegisterTask registerTask) {
        //task 를 저장하며, 하위에 있는 action 도 모두 entity로 바꿔서 저장해줘야 한다.
        Task initTask = registerTask.toEntity(project);
        Task task = projectStore.store(initTask);
        registerTask.getRegisterActionList().forEach(registerAction -> {
            var initAction = registerAction.toEntity(initTask);
            projectStore.store(initAction);
        });

        return task;
    }

    //action 만 개별 단위로 저장할 때 사용
    @Override
    public Action storeAction(Task task, ProjectCommand.RegisterAction registerAction) {
        Action initAction = registerAction.toEntity(task);
        return projectStore.store(initAction);
    }
}
