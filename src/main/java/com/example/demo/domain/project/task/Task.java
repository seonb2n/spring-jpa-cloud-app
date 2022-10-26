package com.example.demo.domain.project.task;

import com.example.demo.common.util.TokenGenerator;
import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.task.action.Action;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 기간 단위로 할 일
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "tasks")
public class Task extends BaseEntity {
    private static final String PREFIX_TASK = "task_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String taskToken;

    //이름, 중요도, 작업기간, 소속 프로젝트, 액션
    private String taskName;

    @Enumerated(value = EnumType.STRING)
    private Importance importance;

    private LocalDateTime startDayTime;
    private LocalDateTime endDayTime;

    @Enumerated(value = EnumType.STRING)
    @Setter
    private Status status;

    @ManyToOne
    @JsonBackReference
    private Project project;
    private String projectToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Action> actionList = new ArrayList<>();

    @Getter
    @RequiredArgsConstructor
    public enum Importance {
        HIGH("중요도_높음"), MIDDLE("중요도_중간"),LOW("중요도_낮음");

        private final String description;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        DONE("완료"), FAIL("실패"), ONGOING("진행중");

        private final String description;
    }

    @Builder
    public Task(String taskName, String importance, LocalDateTime startDayTime, LocalDateTime endDayTime, Project project, String projectToken, List<Action> actionList, String status) {
        taskToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_TASK);
        this.taskName = taskName;
        this.startDayTime = startDayTime;
        this.endDayTime = endDayTime;
        this.project = project;
        this.importance = Importance.valueOf(importance.toUpperCase());
        this.projectToken = projectToken;
        this.actionList = actionList;
        this.status = Status.valueOf(status.toUpperCase());
    }

    public void updateTask(Project project, ProjectCommand.UpdateTask updateTask) {
        this.project = project;
        this.projectToken = project.getProjectToken();
        this.taskName = updateTask.getTaskName();
        this.importance = Importance.valueOf(updateTask.getImportance().toUpperCase());
        this.startDayTime = updateTask.getStartDayTime();
        this.endDayTime = updateTask.getEndDayTime();
        this.status = Status.valueOf(updateTask.getStatus().toUpperCase());
    }
}
