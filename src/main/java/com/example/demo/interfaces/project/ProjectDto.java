package com.example.demo.interfaces.project;

import com.example.demo.domain.project.task.action.Action;
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

    @Getter
    @Setter
    @ToString
    public static class UpdateProjectRequest {

        @NotNull(message = "UserToken 이 필요합니다")
        private String userToken;

        private String projectToken;

        @NotNull(message = "Project 이름은 필수값입니다.")
        private String projectName;

        private String endDayTime;

        private List<UpdateTaskRequest> updateTaskList;

    }

    @Getter
    @Setter
    @ToString
    public static class UpdateTaskRequest {
        private String projectToken;

        private String taskToken;

        @NotNull(message = "Task 이름은 필수값입니다.")
        private String taskName;

        private String importance;

        private String startDayTime;

        private String endDayTime;

        private List<UpdateActionRequest> updateActionList;

    }

    @Getter
    @Setter
    @ToString
    public static class UpdateActionRequest {

        private String taskToken;

        private String actionToken;

        @NotNull(message = "액션 내용을 추가해주셔야 합니다.")
        private String content;

        private String actionStatus;
    }


    @Getter
    @Setter
    @ToString
    public static class GetProjectWithProjectToken{

        private String projectToken;

    }

    //조회 및 등록 결과에 대한 Project Response
    @Getter
    @Setter
    @ToString
    @Builder
    public static class Main {
        private final Long projectId;
        private final String projectToken;
        private final String userToken;
        private final String projectName;
        private final String endDayTime;
        private final List<Task> taskList;
    }

    @Getter
    @Setter
    @ToString
    @Builder
    public static class Task {
        private final Long taskId;
        private final String taskToken;
        private final String taskName;
        private final String importance;
        private final String startDayTime;
        private final String endDayTime;
        private final List<Action> actionList;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class Action {
        private final Long actionId;
        private final String actionToken;
        private final String content;
        private final com.example.demo.domain.project.task.action.Action.ActionStatus actionStatus;
    }

}
