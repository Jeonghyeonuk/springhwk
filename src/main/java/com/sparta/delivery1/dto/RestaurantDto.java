package com.sparta.delivery1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RestaurantDto {

    private String name;            // 음식점 이름


    private int minOrderPrice;      // 최소 주문 가격


    private int deliveryFee;        // 기본 배달비


}
