package com.sparta.delivery1.service;

import com.sparta.delivery1.domain.Food;
import com.sparta.delivery1.domain.Restaurant;
import com.sparta.delivery1.dto.FoodRequestDto;
import com.sparta.delivery1.repository.FoodRepository;
import com.sparta.delivery1.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class FoodService {
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(RestaurantRepository restaurantRepository, FoodRepository foodRepository) {
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
    }


    //음식 등록
    @Transactional
    public void addRestaurantFoods(Long restaurantId, List<FoodRequestDto> foodRequestDto) {
     Restaurant foundRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(
             () -> new IllegalArgumentException("해당하는 음식점이 존재하지 않습니다.")
     );
        for (FoodRequestDto requestDto : foodRequestDto) {
                //받아야될것 : 주는정보
            String foodName = requestDto.getName();
            int foodPrice = requestDto.getPrice();

            checkDuplicateRestaurantFood(foundRestaurant, foodName);
            checkFoodPrice(foodPrice);

            Food food = new Food(foodName, foodPrice, foundRestaurant);
            foodRepository.save(food);
        }
    }

    //음식 조회
    @Transactional
    public List<Food> findAllRestaurantFoods(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new IllegalArgumentException("해당 음식점이 존재하지 않습니다.")
        );
        return foodRepository.findFoodsByRestaurant(restaurant);
    }



    private void checkDuplicateRestaurantFood(Restaurant Restaurant, String foodName) {
        Optional<Food> found = foodRepository.findFoodByRestaurantAndName(Restaurant, foodName);
        if (found.isPresent())
            throw new IllegalArgumentException("이미 존재하는 음식명입니다.");
    }

    private void checkFoodPrice(int foodPrice) {
        if (foodPrice < 100 || foodPrice > 1000000)
            throw new IllegalArgumentException("음식의 가격을 100 ~ 1,000,000원 사이로 설정해주세요");

        if (foodPrice % 100 > 0)
            throw new IllegalArgumentException("음식의 가격은 100원 단위로만 설정 할 수 있습니다.");
    }


}



