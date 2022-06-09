package com.sparta.delivery1.repository;

import com.sparta.delivery1.domain.Food;
import com.sparta.delivery1.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName);


    List<Food> findFoodsByRestaurant(Restaurant restaurant);
}
