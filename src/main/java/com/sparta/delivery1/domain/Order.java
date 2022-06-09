package com.sparta.delivery1.domain;

import com.sparta.delivery1.dto.Order.OrderFoodResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity(name="orderFood")
@Setter
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private int deliveryFee;

    @ManyToOne
    @JoinColumn(name = "FOOD_ID", nullable = false)
    private Restaurant restaurant;

    @OneToMany
    @JoinColumn(name = "ORDER_ID")
    private List<OrderDetails> orders;


    public Order(int totalPrice, int deliveryFee, Restaurant restaurant, List<OrderDetails> orders) {
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
        this.restaurant = restaurant;
        this.orders = orders;
    }
}


