package com.example.demo.domain.postIt.category;

import com.example.demo.common.util.TokenGenerator;
import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.postIt.PostIt;
import com.example.demo.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {
    private static final String PREFIX_CATEGORY = "category_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;
    private String categoryToken;
    private String categoryName;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String userToken;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<PostIt> postItList = new ArrayList<>();

    @Builder
    public Category(User user, String categoryName) {
        this.categoryToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_CATEGORY);
        this.user = user;
        this.userToken = user.getUserToken();
        this.categoryName = categoryName;
    }

    public void updateCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
