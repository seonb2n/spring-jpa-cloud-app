package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String userName;
    private String userLocation;
    private Level level;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<Order> orderHistory = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<Order> orderOnGoing = new ArrayList<>();

}
