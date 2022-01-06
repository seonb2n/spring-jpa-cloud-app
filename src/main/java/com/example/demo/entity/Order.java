package com.example.demo.entity;

import com.example.demo.support.OrderStatus;
import lombok.Builder;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany
    private List<Food> orderedFoods;

    private int totalPrice;

    private OrderStatus orderStatus;

    private String orderStartLocation;
    private String orderEndLocation;

    public int calculateTotalAmount() {

        for (Food orderedFood : orderedFoods) {
            totalPrice = 0;
            totalPrice += orderedFood.getPrice();
        }
        return totalPrice;
    }

    @PrePersist
    public void initOrder() {
        orderStartLocation = restaurant.getRestaurantLocation();
        orderEndLocation = user.getUserLocation();
    }

}
