package com.example.demo.domain.project;

import com.example.demo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
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
        private final List<Task> taskList = new ArrayList<>();
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
        private final Project project;
        private final List<Action> actionList;
    }

    @Getter
    @Builder
    @ToString
    public static class Action {
        private Long actionId;
        private Task task;
        private String content;
    }
}
