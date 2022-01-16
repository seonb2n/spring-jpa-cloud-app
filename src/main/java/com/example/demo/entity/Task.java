package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private int importance;

    private String startTime;
    private String endTime;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "task")
    private List<Action> actionList = new java.util.ArrayList<>();

}