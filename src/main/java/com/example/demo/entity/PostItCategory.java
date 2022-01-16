package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class PostItCategory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<PostIt> postItList;
}
