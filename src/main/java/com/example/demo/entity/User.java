package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Column(name = "user_id")
    private Long id;

    private String userEmail;
    private String password;
    private String userNickName;

    private String startDayTime;
    private String endDayTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Project> projectList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<PostIt> postItList = new ArrayList<>();
}