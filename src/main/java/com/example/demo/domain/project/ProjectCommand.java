package com.example.demo.domain.project;

import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.project.task.action.Action;
import com.example.demo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterProject {

        private String userToken;
        private String projectName;
        private String endDayTime;
        private List<RegisterTask> registerTaskList;

        public Project toEntity(User user) {
            return Project.builder()
                    .user(user)
                    .userToken(userToken)
                    .projectName(projectName)
                    .endDayTime(endDayTime)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterTask {

        private String taskName;
        private String importance;
        private String startDayTime;
        private String endDayTime;
        private String projectToken;
        private List<RegisterAction> registerActionList;

        public Task toEntity(Project project) {
            //project 가 지정되어 있지 않은 경우의 처리가 필요하다.
            if(projectToken == null || projectToken.equals("")) {
                return Task.builder()
                        .project(project)
                        .projectToken(project.getProjectToken())
                        .importance(importance)
                        .taskName(taskName)
                        .startDayTime(startDayTime)
                        .endDayTime(endDayTime)
                        .status("ONGOING")
                        .build();
            }
            return Task.builder()
                    .project(project)
                    .projectToken(projectToken)
                    .taskName(taskName)
                    .importance(importance)
                    .startDayTime(startDayTime)
                    .endDayTime(endDayTime)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterAction {

        private String taskToken;
        private String content;

        public Action toEntity(Task task) {
            Action initAction;
            if(taskToken == null || taskToken.equals("")) {
                initAction = Action.builder()
                        .content(content)
                        .task(task)
                        .taskToken(task.getTaskToken())
                        .actionStatus("UNDONE")
                        .build();
            }
            else {
                initAction =  Action.builder()
                        .content(content)
                        .task(task)
                        .taskToken(taskToken)
                        .actionStatus("UNDONE")
                        .build();
            }

            return initAction;
        }
    }

    @Getter
    @Builder
    @ToString
    public static class UpdateProject {
        private String userToken;
        private String projectToken;
        private String projectName;
        private String endDayTime;
        private List<UpdateTask> updateTaskList;

    }

    @Getter
    @Builder
    @ToString
    public static class UpdateTask {
        private String taskToken;
        private String taskName;
        private String importance;
        private String startDayTime;
        private String endDayTime;
        private String projectToken;
        private List<UpdateAction> updateActionList;
        private String status;

        public Task toEntity(Project project) {
            return Task.builder()
                    .taskName(taskName)
                    .project(project)
                    .projectToken(projectToken)
                    .importance(importance)
                    .startDayTime(startDayTime)
                    .endDayTime(endDayTime)
                    .status(status)
                    .build();
        }

        public RegisterTask toRegisterTask(Project project) {
            return RegisterTask.builder()
                    .taskName(taskName)
                    .projectToken(project.getProjectToken())
                    .importance(importance)
                    .startDayTime(startDayTime)
                    .endDayTime(endDayTime)
                    .registerActionList(updateActionList.stream()
                            .map(UpdateAction::toRegisterAction).collect(Collectors.toList()))
                    .build();

        }
    }

    @Getter
    @Builder
    @ToString
    public static class UpdateAction {
        private String taskToken;
        private String actionToken;
        private String content;
        private String actionStatus;

        public Action toEntity(Task task) {
            return Action.builder()
                    .task(task)
                    .taskToken(taskToken)
                    .content(content)
                    .actionStatus(actionStatus)
                    .build();
        }

        public RegisterAction toRegisterAction() {
            return RegisterAction.builder()
                    .content(content)
                    .build();
        }
    }
}
