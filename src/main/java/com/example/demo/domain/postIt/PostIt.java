package com.example.demo.domain.postIt;

import com.example.demo.common.util.TokenGenerator;
import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.postIt.category.Category;
import com.example.demo.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "postits")
public class PostIt extends BaseEntity {
    private static final String PREFIX_POSTIT = "postIt_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postit_id", nullable = false)
    private Long id;
    private String postItToken;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String userToken;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String categoryName;

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
    public PostIt(User user, String userToken, String content, String status, Category category, String categoryName) {

        this.postItToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_POSTIT);
        this.user = user;
        this.userToken = userToken;
        this.content = content;
        this.category = category;
        if(categoryName == null) {
            this.categoryName = category.getCategoryName();
        } else {
            this.categoryName = categoryName;
        }
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
