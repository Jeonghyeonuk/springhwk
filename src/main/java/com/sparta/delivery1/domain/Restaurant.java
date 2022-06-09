package com.sparta.delivery1.domain;

import com.sparta.delivery1.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;            // 음식점 이름

    @Column(nullable = false)
    private int minOrderPrice;      // 최소 주문 가격

    @Column(nullable = false)
    private int deliveryFee;        // 기본 배달비

    public Restaurant(@NotNull RestaurantDto restaurantDto) {
        this.name = restaurantDto.getName();
        this.minOrderPrice = restaurantDto.getMinOrderPrice();
        this.deliveryFee = restaurantDto.getDeliveryFee();
    }


}
