package com.example.demo.domain.postIt.category;

import com.example.demo.common.util.TokenGenerator;
import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.postIt.PostIt;
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

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<PostIt> postItList = new ArrayList<>();

    @Builder
    public Category(String categoryName, PostIt postIt) {

        this.categoryToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_CATEGORY);
        this.categoryName = categoryName;
        postItList.add(postIt);

    }
}
