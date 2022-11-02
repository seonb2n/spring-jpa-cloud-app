package com.example.demo.interfaces.project;

import com.example.demo.domain.project.ProjectCommand.RegisterAction;
import com.example.demo.domain.project.ProjectCommand.RegisterAction.RegisterActionBuilder;
import com.example.demo.domain.project.ProjectCommand.RegisterProject;
import com.example.demo.domain.project.ProjectCommand.RegisterProject.RegisterProjectBuilder;
import com.example.demo.domain.project.ProjectCommand.RegisterTask;
import com.example.demo.domain.project.ProjectCommand.RegisterTask.RegisterTaskBuilder;
import com.example.demo.domain.project.ProjectCommand.UpdateAction;
import com.example.demo.domain.project.ProjectCommand.UpdateAction.UpdateActionBuilder;
import com.example.demo.domain.project.ProjectCommand.UpdateProject;
import com.example.demo.domain.project.ProjectCommand.UpdateProject.UpdateProjectBuilder;
import com.example.demo.domain.project.ProjectCommand.UpdateTask;
import com.example.demo.domain.project.ProjectCommand.UpdateTask.UpdateTaskBuilder;
import com.example.demo.domain.project.ProjectInfo.ActionInfo;
import com.example.demo.domain.project.ProjectInfo.TaskInfo;
import com.example.demo.interfaces.project.ProjectDto.Action;
import com.example.demo.interfaces.project.ProjectDto.Action.ActionBuilder;
import com.example.demo.interfaces.project.ProjectDto.Main;
import com.example.demo.interfaces.project.ProjectDto.Main.MainBuilder;
import com.example.demo.interfaces.project.ProjectDto.RegisterActionRequest;
import com.example.demo.interfaces.project.ProjectDto.RegisterProjectRequest;
import com.example.demo.interfaces.project.ProjectDto.RegisterTaskRequest;
import com.example.demo.interfaces.project.ProjectDto.Task;
import com.example.demo.interfaces.project.ProjectDto.Task.TaskBuilder;
import com.example.demo.interfaces.project.ProjectDto.UpdateActionRequest;
import com.example.demo.interfaces.project.ProjectDto.UpdateProjectRequest;
import com.example.demo.interfaces.project.ProjectDto.UpdateTaskRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-30T21:08:13+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class ProjectDtoMapperImpl implements ProjectDtoMapper {

    @Override
    public RegisterProject of(RegisterProjectRequest registerProjectRequest) {
        if ( registerProjectRequest == null ) {
            return null;
        }

        RegisterProjectBuilder registerProject = RegisterProject.builder();

        registerProject.userToken( registerProjectRequest.getUserToken() );
        registerProject.projectName( registerProjectRequest.getProjectName() );
        registerProject.endDayTime( registerProjectRequest.getEndDayTime() );
        registerProject.registerTaskList( registerTaskRequestListToRegisterTaskList( registerProjectRequest.getRegisterTaskList() ) );

        return registerProject.build();
    }

    @Override
    public RegisterTask of(RegisterTaskRequest registerTaskRequest) {
        if ( registerTaskRequest == null ) {
            return null;
        }

        RegisterTaskBuilder registerTask = RegisterTask.builder();

        registerTask.taskName( registerTaskRequest.getTaskName() );
        registerTask.importance( registerTaskRequest.getImportance() );
        if ( registerTaskRequest.getStartDayTime() != null ) {
            registerTask.startDayTime( LocalDateTime.parse( registerTaskRequest.getStartDayTime() ) );
        }
        if ( registerTaskRequest.getEndDayTime() != null ) {
            registerTask.endDayTime( LocalDateTime.parse( registerTaskRequest.getEndDayTime() ) );
        }
        registerTask.projectToken( registerTaskRequest.getProjectToken() );
        registerTask.registerActionList( registerActionRequestListToRegisterActionList( registerTaskRequest.getRegisterActionList() ) );

        return registerTask.build();
    }

    @Override
    public RegisterAction of(RegisterActionRequest registerActionRequest) {
        if ( registerActionRequest == null ) {
            return null;
        }

        RegisterActionBuilder registerAction = RegisterAction.builder();

        registerAction.taskToken( registerActionRequest.getTaskToken() );
        registerAction.content( registerActionRequest.getContent() );

        return registerAction.build();
    }

    @Override
    public Main of(com.example.demo.domain.project.ProjectInfo.Main projectMainInfo) {
        if ( projectMainInfo == null ) {
            return null;
        }

        MainBuilder main = Main.builder();

        main.projectId( projectMainInfo.getProjectId() );
        main.projectToken( projectMainInfo.getProjectToken() );
        main.userToken( projectMainInfo.getUserToken() );
        main.projectName( projectMainInfo.getProjectName() );
        main.endDayTime( projectMainInfo.getEndDayTime() );
        main.taskList( taskInfoListToTaskList( projectMainInfo.getTaskList() ) );

        return main.build();
    }

    @Override
    public Task of(TaskInfo taskInfo) {
        if ( taskInfo == null ) {
            return null;
        }

        TaskBuilder task = Task.builder();

        task.taskId( taskInfo.getTaskId() );
        task.taskToken( taskInfo.getTaskToken() );
        task.taskName( taskInfo.getTaskName() );
        task.importance( taskInfo.getImportance() );
        task.startDayTime( taskInfo.getStartDayTime() );
        task.endDayTime( taskInfo.getEndDayTime() );
        task.actionList( actionInfoListToActionList( taskInfo.getActionList() ) );

        return task.build();
    }

    @Override
    public Action of(ActionInfo actionInfo) {
        if ( actionInfo == null ) {
            return null;
        }

        ActionBuilder action = Action.builder();

        action.actionId( actionInfo.getActionId() );
        action.actionToken( actionInfo.getActionToken() );
        action.content( actionInfo.getContent() );
        action.actionStatus( actionInfo.getActionStatus() );

        return action.build();
    }

    @Override
    public UpdateProject of(UpdateProjectRequest updateProjectRequest) {
        if ( updateProjectRequest == null ) {
            return null;
        }

        UpdateProjectBuilder updateProject = UpdateProject.builder();

        updateProject.userToken( updateProjectRequest.getUserToken() );
        updateProject.projectToken( updateProjectRequest.getProjectToken() );
        updateProject.projectName( updateProjectRequest.getProjectName() );
        updateProject.endDayTime( updateProjectRequest.getEndDayTime() );
        updateProject.updateTaskList( updateTaskRequestListToUpdateTaskList( updateProjectRequest.getUpdateTaskList() ) );

        return updateProject.build();
    }

    @Override
    public UpdateTask of(UpdateTaskRequest updateTaskRequest) {
        if ( updateTaskRequest == null ) {
            return null;
        }

        UpdateTaskBuilder updateTask = UpdateTask.builder();

        updateTask.taskToken( updateTaskRequest.getTaskToken() );
        updateTask.taskName( updateTaskRequest.getTaskName() );
        updateTask.importance( updateTaskRequest.getImportance() );
        if ( updateTaskRequest.getStartDayTime() != null ) {
            updateTask.startDayTime( LocalDateTime.parse( updateTaskRequest.getStartDayTime() ) );
        }
        if ( updateTaskRequest.getEndDayTime() != null ) {
            updateTask.endDayTime( LocalDateTime.parse( updateTaskRequest.getEndDayTime() ) );
        }
        updateTask.projectToken( updateTaskRequest.getProjectToken() );
        updateTask.updateActionList( updateActionRequestListToUpdateActionList( updateTaskRequest.getUpdateActionList() ) );

        return updateTask.build();
    }

    @Override
    public UpdateAction of(UpdateActionRequest updateActionRequest) {
        if ( updateActionRequest == null ) {
            return null;
        }

        UpdateActionBuilder updateAction = UpdateAction.builder();

        updateAction.taskToken( updateActionRequest.getTaskToken() );
        updateAction.actionToken( updateActionRequest.getActionToken() );
        updateAction.content( updateActionRequest.getContent() );
        updateAction.actionStatus( updateActionRequest.getActionStatus() );

        return updateAction.build();
    }

    protected List<RegisterTask> registerTaskRequestListToRegisterTaskList(List<RegisterTaskRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<RegisterTask> list1 = new ArrayList<RegisterTask>( list.size() );
        for ( RegisterTaskRequest registerTaskRequest : list ) {
            list1.add( of( registerTaskRequest ) );
        }

        return list1;
    }

    protected List<RegisterAction> registerActionRequestListToRegisterActionList(List<RegisterActionRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<RegisterAction> list1 = new ArrayList<RegisterAction>( list.size() );
        for ( RegisterActionRequest registerActionRequest : list ) {
            list1.add( of( registerActionRequest ) );
        }

        return list1;
    }

    protected List<Task> taskInfoListToTaskList(List<TaskInfo> list) {
        if ( list == null ) {
            return null;
        }

        List<Task> list1 = new ArrayList<Task>( list.size() );
        for ( TaskInfo taskInfo : list ) {
            list1.add( of( taskInfo ) );
        }

        return list1;
    }

    protected List<Action> actionInfoListToActionList(List<ActionInfo> list) {
        if ( list == null ) {
            return null;
        }

        List<Action> list1 = new ArrayList<Action>( list.size() );
        for ( ActionInfo actionInfo : list ) {
            list1.add( of( actionInfo ) );
        }

        return list1;
    }

    protected List<UpdateTask> updateTaskRequestListToUpdateTaskList(List<UpdateTaskRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<UpdateTask> list1 = new ArrayList<UpdateTask>( list.size() );
        for ( UpdateTaskRequest updateTaskRequest : list ) {
            list1.add( of( updateTaskRequest ) );
        }

        return list1;
    }

    protected List<UpdateAction> updateActionRequestListToUpdateActionList(List<UpdateActionRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<UpdateAction> list1 = new ArrayList<UpdateAction>( list.size() );
        for ( UpdateActionRequest updateActionRequest : list ) {
            list1.add( of( updateActionRequest ) );
        }

        return list1;
    }
}
