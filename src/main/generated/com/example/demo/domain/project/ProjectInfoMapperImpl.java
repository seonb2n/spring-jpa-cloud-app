package com.example.demo.domain.project;

import com.example.demo.domain.project.ProjectInfo.ActionInfo;
import com.example.demo.domain.project.ProjectInfo.ActionInfo.ActionInfoBuilder;
import com.example.demo.domain.project.ProjectInfo.Main;
import com.example.demo.domain.project.ProjectInfo.Main.MainBuilder;
import com.example.demo.domain.project.ProjectInfo.TaskInfo;
import com.example.demo.domain.project.ProjectInfo.TaskInfo.TaskInfoBuilder;
import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.project.task.action.Action;
import java.time.format.DateTimeFormatter;
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
public class ProjectInfoMapperImpl implements ProjectInfoMapper {

    @Override
    public Main of(Project project, List<Task> taskList) {
        if ( project == null && taskList == null ) {
            return null;
        }

        MainBuilder main = Main.builder();

        if ( project != null ) {
            main.projectId( project.getProjectId() );
            main.projectToken( project.getProjectToken() );
            main.userToken( project.getUserToken() );
            main.projectName( project.getProjectName() );
            main.endDayTime( project.getEndDayTime() );
            main.taskList( taskListToTaskInfoList( project.getTaskList() ) );
        }

        return main.build();
    }

    @Override
    public TaskInfo of(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskInfoBuilder taskInfo = TaskInfo.builder();

        taskInfo.taskId( task.getTaskId() );
        taskInfo.taskToken( task.getTaskToken() );
        taskInfo.taskName( task.getTaskName() );
        if ( task.getImportance() != null ) {
            taskInfo.importance( task.getImportance().name() );
        }
        if ( task.getStartDayTime() != null ) {
            taskInfo.startDayTime( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( task.getStartDayTime() ) );
        }
        if ( task.getEndDayTime() != null ) {
            taskInfo.endDayTime( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( task.getEndDayTime() ) );
        }
        taskInfo.project( task.getProject() );
        taskInfo.actionList( actionListToActionInfoList( task.getActionList() ) );

        return taskInfo.build();
    }

    @Override
    public ActionInfo of(Action action) {
        if ( action == null ) {
            return null;
        }

        ActionInfoBuilder actionInfo = ActionInfo.builder();

        actionInfo.actionId( action.getActionId() );
        actionInfo.actionToken( action.getActionToken() );
        actionInfo.task( action.getTask() );
        actionInfo.content( action.getContent() );
        actionInfo.actionStatus( action.getActionStatus() );

        return actionInfo.build();
    }

    protected List<TaskInfo> taskListToTaskInfoList(List<Task> list) {
        if ( list == null ) {
            return null;
        }

        List<TaskInfo> list1 = new ArrayList<TaskInfo>( list.size() );
        for ( Task task : list ) {
            list1.add( of( task ) );
        }

        return list1;
    }

    protected List<ActionInfo> actionListToActionInfoList(List<Action> list) {
        if ( list == null ) {
            return null;
        }

        List<ActionInfo> list1 = new ArrayList<ActionInfo>( list.size() );
        for ( Action action : list ) {
            list1.add( of( action ) );
        }

        return list1;
    }
}
