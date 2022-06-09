package com.sparta.delivery1.dto.Order;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponseDto {
    private int totalPrice;

    private int deliveryFee;

    private String restaurantName;

    List<OrderFoodResponseDto> foods;

    public OrderResponseDto(String restaurantName, int totalPrice, int deliveryFee, List<OrderFoodResponseDto> foods) {
        this.restaurantName = restaurantName;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
        this.foods = foods;
    }
}


