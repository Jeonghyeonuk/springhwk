package com.sparta.delivery1.service;

import com.sparta.delivery1.domain.Restaurant;
import com.sparta.delivery1.dto.RestaurantDto;
import com.sparta.delivery1.repository.RestaurantRepository;
import com.sparta.delivery1.validator.RestaurantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Transactional
    public Restaurant registerRestaurant(RestaurantDto restaurantDto) {

        // 유효성 검사
        RestaurantValidator.validRestaurant(restaurantDto);

        //음식점 등록
        Restaurant restaurant = new Restaurant(restaurantDto);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public List<Restaurant> restaurantList() {
        //모든 음식점 조회
        return restaurantRepository.findAll();
    }
}
