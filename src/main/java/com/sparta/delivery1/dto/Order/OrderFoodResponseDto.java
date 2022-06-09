package com.sparta.delivery1.dto.Order;

import lombok.Getter;

@Getter
public class OrderFoodResponseDto {
    private String foodName;

    private int quantity;

    private int foodPrice;

    public OrderFoodResponseDto(String foodName, int quantity, int foodPrice) {
        this.foodName = foodName;
        this.quantity = quantity;
        this.foodPrice = foodPrice;
    }
}
