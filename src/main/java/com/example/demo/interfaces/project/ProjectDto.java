package com.example.demo.interfaces.project;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.ProjectInfo;
import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ProjectDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterProjectRequest {

        @NotNull(message = "UserToken 이 필요합니다")
        private String userToken;

        @NotNull(message = "Project 이름은 필수값입니다.")
        private String projectName;

        private String endDayTime;

        private List<RegisterTaskRequest> registerTaskList;

    }

    @Getter
    @Setter
    @ToString
    public static class RegisterTaskRequest {

        @NotNull(message = "Task 이름은 필수값입니다.")
        private String taskName;

        private String importance;

        private String startDayTime;

        private String endDayTime;

        private String projectToken;

        private List<RegisterActionRequest> registerActionList;

    }

    @Getter
    @Setter
    @ToString
    public static class RegisterActionRequest {

        private String taskToken;

        @NotNull(message = "액션 내용을 추가해주셔야 합니다.")
        private String content;
    }

    //조회 및 등록 결과에 대한 Project Response
    @Getter
    @Builder
    @ToString
    public static class Main {
        private final Long projectId;
        private final String projectToken;
        private final String userToken;
        private final String projectName;
        private final String endDayTime;
        private final List<Task> taskList;

        public Main(ProjectInfo.Main main) {
            this.projectId = main.getProjectId();
            this.projectToken = main.getProjectToken();
            this.userToken = main.getUserToken();
            this.projectName = main.getProjectName();
            this.endDayTime = main.getEndDayTime();
            this.taskList = new ArrayList<>();
            main.getTaskList().forEach(taskInfo -> taskList.add(new Task(taskInfo)));
        }
    }

    @Getter
    @Builder
    @ToString
    public static class Task {
        private final Long taskId;
        private final String taskToken;
        private final String taskName;
        private final String importance;
        private final String startDayTime;
        private final String endDayTime;
        private final List<Action> actionList;

        public Task(ProjectInfo.TaskInfo taskInfo) {
            this.taskId = taskInfo.getTaskId();
            this.taskToken = taskInfo.getTaskToken();
            this.taskName = taskInfo.getTaskName();
            this.importance = taskInfo.getImportance();
            this.startDayTime = taskInfo.getStartDayTime();
            this.endDayTime = taskInfo.getEndDayTime();
            this.actionList = new ArrayList<>();
            taskInfo.getActionList().forEach(actionInfo -> actionList.add(new Action(actionInfo)));
        }
    }

    @Getter
    @Builder
    @ToString
    public static class Action {
        private final Long actionId;
        private final String actionToken;
        private final String content;

        public Action(ProjectInfo.ActionInfo actionInfo) {
            this.actionId = actionInfo.getActionId();
            this.content = actionInfo.getContent();
            this.actionToken = actionInfo.getActionToken();
        }
    }

}
