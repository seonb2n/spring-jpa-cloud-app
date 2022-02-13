package com.example.demo.domain.post;

import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostIt extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postit_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;
    private PostItStatus status;

    @Getter
    @RequiredArgsConstructor
    public enum PostItStatus {
        COMPLETE("완료된 포스트잇"),
        INCOMPLETE("미완료된 포스트잇");

        private final String description;
    }
}
