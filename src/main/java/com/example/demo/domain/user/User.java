package com.example.demo.domain.user;

import com.example.demo.common.util.TokenGenerator;
import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.postIt.PostIt;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
@Entity
@Getter
@Table(name = "users")
public class User extends BaseEntity {
    private static final String PREFIX_USER = "user_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userToken;

    private String userEmail;
    private String password;
    private String userNickName;

    private String startDayTime;
    private String endDayTime;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<PostIt> postItList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Getter
    @RequiredArgsConstructor
    public enum UserStatus {
        ACTIVE("활성화"), INACTIVE("비활성화");

        private final String description;
    }

    //builder 패턴으로 user Entity 생성
    @Builder
    public User(String userEmail, String password, String userNickName, String startDayTime, String endDayTime) {

        //TODO UserEmail 이 이미 존재하는 Email 인지 검증하는 로직 필요

        this.userToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_USER);
        this.userEmail = userEmail;
        this.password = password;
        this.userNickName = userNickName;
        this.startDayTime = startDayTime;
        this.endDayTime = endDayTime;
        this.status = UserStatus.ACTIVE;
    }

    public void activeUser() {
        this.status = UserStatus.ACTIVE;
    }

    public void inactiveUser() {
        this.status = UserStatus.INACTIVE;
    }

    public void changePassword(String password) {
        this.password = password;
    }
}