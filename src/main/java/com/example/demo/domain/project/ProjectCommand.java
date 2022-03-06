package com.example.demo.domain.project;

import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.project.task.action.Action;
import com.example.demo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

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
            Task.Importance enumImportance;

            if(importance.equals("HIGH")) {
                enumImportance = Task.Importance.HIGH;
            }
            else if(importance.equals("MIDDLE")) {
                enumImportance = Task.Importance.MIDDLE;
            }
            else {
                enumImportance = Task.Importance.LOW;
            }

            //project 가 지정되어 있지 않은 경우의 처리가 필요하다.
            if(projectToken == null || projectToken.equals("")) {
                return Task.builder()
                        .project(project)
                        .projectToken(project.getProjectToken())
                        .importance(enumImportance)
                        .taskName(taskName)
                        .startDayTime(startDayTime)
                        .endDayTime(endDayTime)
                        .build();
            }

            return Task.builder()
                    .project(project)
                    .projectToken(projectToken)
                    .taskName(taskName)
                    .importance(enumImportance)
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

            if(taskToken == null || taskToken.equals("")) {
                return Action.builder()
                        .content(content)
                        .task(task)
                        .taskToken(task.getTaskToken())
                        .build();
            }

            return Action.builder()
                    .content(content)
                    .task(task)
                    .taskToken(taskToken)
                    .build();
        }

    }
}
