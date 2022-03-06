package com.example.demo.domain.project.task.action;

import com.example.demo.common.util.TokenGenerator;
import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.project.task.Task;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "actions")
public class Action extends BaseEntity {

    private static final String PREFIX_ACTION = "action_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actionId;
    private String actionToken;

    @ManyToOne
    @JsonBackReference
    private Task task;
    private String taskToken;

    private String content;

    @Builder
    public Action(Task task, String content, String taskToken) {
        this.actionToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_ACTION);
        this.task = task;
        this.taskToken = taskToken;
        this.content = content;
    }
}
