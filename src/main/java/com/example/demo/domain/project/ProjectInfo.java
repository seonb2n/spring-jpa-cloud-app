package com.example.demo.domain.project;

import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.project.task.action.Action;
import com.example.demo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class ProjectInfo {

    @Getter
    @ToString
    @Builder
    public static class Main {
        private final Long projectId;
        private final String projectToken;
        private final String userToken;
        private final String projectName;
        private final String endDayTime;
        private final List<TaskInfo> taskList;

    }

    @Getter
    @ToString
    @Builder
    public static class TaskInfo {
        private final Long taskId;
        private final String taskToken;
        private final String taskName;
        private final String importance;
        private final String startDayTime;
        private final String endDayTime;
        private final Project project;
        private final List<ActionInfo> actionList;

    }

    @Getter
    @Builder
    @ToString
    public static class ActionInfo {
        private final Long actionId;
        private final String actionToken;
        private final Task task;
        private final String content;

    }
}
