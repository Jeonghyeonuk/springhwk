package com.sparta.delivery1.controller;

import com.sparta.delivery1.domain.Food;
import com.sparta.delivery1.dto.FoodRequestDto;
import com.sparta.delivery1.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FoodController {
    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    //음식점 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    @ResponseBody
    public void registerFood(@PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> foodRequestDto) {
        foodService.addRestaurantFoods(restaurantId, foodRequestDto);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    @ResponseBody
    public List<Food> readRestaurantFoods(@PathVariable Long restaurantId){
        return foodService.findAllRestaurantFoods(restaurantId);
    }
}
