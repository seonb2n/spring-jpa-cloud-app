package com.example.demo.domain.project;

import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.project.task.action.Action;
import com.example.demo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

public class ProjectInfo {

    @Getter
    @Builder
    @ToString
    public static class Main {
        private final Long projectId;
        private final String projectToken;
        private final User user;
        private final String userToken;
        private final String projectName;
        private final String endDayTime;
        private final List<Task> taskList;

        public Main(Project project) {
            this.projectId = project.getProjectId();
            this.projectToken = project.getProjectToken();
            this.user = project.getUser();
            this.userToken = project.getUserToken();
            this.projectName = project.getProjectName();
            this.endDayTime = project.getEndDayTime();
            this.taskList = project.getTaskList();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class TaskInfo {
        private final Long taskId;
        private final String taskToken;
        private final String taskName;
        private final String importance;
        private final String startDayTime;
        private final String endDayTime;
        private final Project project;
        private final List<Action> actionList;

        public TaskInfo(Project project, Task task) {
            this.taskId = task.getId();
            this.taskToken = task.getTaskToken();
            this.taskName = task.getTaskName();
            this.importance = task.getImportance().toString();
            this.startDayTime = task.getStartDayTime();
            this.endDayTime = task.getEndDayTime();
            if(project == null) {
                this.project = null;
            } else {
                this.project = task.getProject();
            }
            this.actionList = task.getActionList();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class ActionInfo {
        private Long actionId;
        private Task task;
        private String content;

        public ActionInfo(Task task, Action action) {
            this.actionId = action.getId();
            this.task = task;
            this.content = action.getContent();
        }
    }
}
