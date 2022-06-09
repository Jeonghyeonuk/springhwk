package com.sparta.delivery1.validator;

import com.sparta.delivery1.dto.RestaurantDto;
import com.sun.nio.sctp.IllegalReceiveException;
import org.springframework.stereotype.Component;

@Component // Bean 등록

public class RestaurantValidator {

    public static void validRestaurant(RestaurantDto restaurantDto) {
        if (restaurantDto.getMinOrderPrice() < 1000 || restaurantDto.getMinOrderPrice() > 100000) {
            throw new IllegalReceiveException("최소주문 가격은 1,000원 ~ 100,00원을 입력해주세요.");
        } else if (restaurantDto.getMinOrderPrice() % 100 != 0) {
            throw new IllegalArgumentException("최소 주문 가격은 100원 단위로만 입력 가능합니다.");
        }

        if (restaurantDto.getDeliveryFee() < 0 || restaurantDto.getDeliveryFee() > 10000) {
            throw new IllegalArgumentException("배달비는 0원 ~ 10,000원을 입력해주세요.");
        } else if (restaurantDto.getDeliveryFee() % 500 != 0) {
            throw new IllegalArgumentException("배달비는 500원 단위로만 입력 가능합니다.");
        }
    }
}
