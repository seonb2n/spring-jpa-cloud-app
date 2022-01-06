package com.example.demo.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orderedList;

    private int dayTotalIncome;

    private int monthTotalIncome;

    private int totalIncome;

    @OneToMany(mappedBy = "restaurant")
    List<Food> menuFoods;

    private String restaurantLocation;

}
