package com.example.demo.domain.postIt;

import com.example.demo.common.util.TokenGenerator;
import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostIt extends BaseEntity {
    private static final String PREFIX_POSTIT = "postIt_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postit_id", nullable = false)
    private Long id;
    private String postItToken;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String userToken;

    private String content;
    private PostItStatus status;

    @Getter
    @RequiredArgsConstructor
    public enum PostItStatus {
        COMPLETE("완료된 포스트잇"),
        INCOMPLETE("미완료된 포스트잇");

        private final String description;
    }

    @Builder
    public PostIt(User user, String userToken, String content, String status) {

        this.postItToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_POSTIT);
        this.user = user;
        this.userToken = userToken;
        this.content = content;
        changePostItStatus(status);
    }

    public void changePostItStatus(String status) {
        if(status.equals("COMPLETE")) {
            completePostIt();
        } else {
            incompletePostIt();
        }
    }

    public void completePostIt() {
        this.status = PostItStatus.COMPLETE;
    }

    public void incompletePostIt() {
        this.status = PostItStatus.INCOMPLETE;
    }

}
