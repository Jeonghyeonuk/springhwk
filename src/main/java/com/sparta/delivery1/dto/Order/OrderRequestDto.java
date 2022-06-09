package com.sparta.delivery1.dto.Order;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private Long restaurantId;
    private List<OrderFoodRequestDto> foods;
}
