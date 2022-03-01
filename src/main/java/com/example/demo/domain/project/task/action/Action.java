package com.example.demo.domain.project.task.action;

import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.project.task.Task;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.signature.qual.BinaryName;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "actions")
public class Action extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Task task;

    private String content;

    @Builder
    public Action(Task task, String content) {
        this.task = task;
        this.content = content;
    }
}
