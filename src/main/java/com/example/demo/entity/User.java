package com.example.demo.entity;

import com.example.demo.support.Level;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String userName;
    private String userLocation;
    private Level level;

    @ToString.Exclude
    @OneToMany
    private List<Order> orderHistory;

    @ToString.Exclude
    @OneToMany
    private List<Order> orderOnGoing;
}