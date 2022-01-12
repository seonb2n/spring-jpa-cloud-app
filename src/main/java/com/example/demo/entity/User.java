package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
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

    

}