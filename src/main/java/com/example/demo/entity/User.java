package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    private String userEmail;
    private String password;
    private String userNickName;

    private String startDayTime;
    private String endDayTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Project> projectList = new java.util.ArrayList<>();
}