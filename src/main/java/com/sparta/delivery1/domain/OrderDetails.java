package com.sparta.delivery1.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private int foodPrice;


    public OrderDetails(int quantity, String foodName, int foodPrice) {
        this.quantity = quantity;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }
}
