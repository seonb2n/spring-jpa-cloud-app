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
        private List<Task> taskList;

        public Project toEntity(User user) {
            return Project.builder()
                    .user(user)
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
        private Project project;

        public Task toEntity(Project project) {
            Task.Importance enumImportance;
            //project 가 지정되어 있지 않은 경우의 처리가 필요하다.
            if(importance.equals("HIGH")) {
                enumImportance = Task.Importance.HIGH;
            }
            else if(importance.equals("MIDDLE")) {
                enumImportance = Task.Importance.MIDDLE;
            }
            else {
                enumImportance = Task.Importance.LOW;
            }

            return Task.builder()
                    .project(project)
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

        private String content;
        private Task task;

        public Action toEntity(Task task) {
            return Action.builder()
                    .content(content)
                    .task(task)
                    .build();
        }

    }
}
