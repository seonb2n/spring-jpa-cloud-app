package com.example.demo.domain.log;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;
    private LocalDateTime createdAt;
    private String content;

    public UserLog(String userEmail, LocalDateTime createdAt, String content) {
        this.userEmail = userEmail;
        this.createdAt = createdAt;
        this.content = content;
    }
}
