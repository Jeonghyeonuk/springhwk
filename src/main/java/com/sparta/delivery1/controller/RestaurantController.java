package com.sparta.delivery1.controller;

import com.sparta.delivery1.domain.Restaurant;
import com.sparta.delivery1.dto.RestaurantDto;
import com.sparta.delivery1.repository.RestaurantRepository;
import com.sparta.delivery1.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RestaurantController {
    private final RestaurantService restaurantService;

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantRepository restaurantRepository) {
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
    }

    //음식점 조회

    @GetMapping("/restaurants")
    @ResponseBody
    public List<Restaurant> readRestaurant(){
        return restaurantService.restaurantList();
    }

    //음식점 등록

    @PostMapping("/restaurant/register")
    @ResponseBody
    public Restaurant registerRestaurant(@RequestBody RestaurantDto restaurantDto){
        return restaurantService.registerRestaurant(restaurantDto);
    }


}
