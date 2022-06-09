package com.sparta.delivery1.service;

import com.sparta.delivery1.domain.Food;
import com.sparta.delivery1.domain.Order;
import com.sparta.delivery1.domain.OrderDetails;
import com.sparta.delivery1.domain.Restaurant;
import com.sparta.delivery1.dto.Order.OrderFoodRequestDto;
import com.sparta.delivery1.dto.Order.OrderFoodResponseDto;
import com.sparta.delivery1.dto.Order.OrderRequestDto;
import com.sparta.delivery1.dto.Order.OrderResponseDto;
import com.sparta.delivery1.repository.FoodRepository;
import com.sparta.delivery1.repository.OrderDetailsRepository;
import com.sparta.delivery1.repository.OrderRepository;
import com.sparta.delivery1.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;

    private final RestaurantRepository restaurantRepository;

    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderService(FoodRepository foodRepository, OrderRepository orderRepository, RestaurantRepository restaurantRepository, OrderDetailsRepository orderDetailsRepository) {
        this.foodRepository = foodRepository;
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Transactional
    public OrderResponseDto getOrders(OrderRequestDto orderRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 식당입니다.")
        ); // 식당 존재하는지 확인

        List<OrderFoodRequestDto> orderFoods = orderRequestDto.getFoods(); // 받아온 주문목록에서 음식관련 정보를 뺌 id, quantity
        List<OrderFoodResponseDto> foods = new ArrayList<>();// for문 돌고나서 저장할 List
        List<OrderDetails> orderDetailsList1 = new ArrayList<>();
//        List<OrderDetails> orderDetails = new ArrayList<>();

        int totalPrice = 0; // 총 가격
        for (int i = 0; i < orderFoods.size(); i++) { // 받은 주문에 있는 음식의 수만큼 반복해서

            if (orderFoods.get(i).getQuantity() < 1 || orderFoods.get(i).getQuantity() > 100) {
                throw new IllegalArgumentException("주문은 1개~100개만 가능합니다.");
            }

            Optional<Food> food = foodRepository.findById(orderFoods.get(i).getId());
            if (!food.isPresent()) {
                throw new IllegalArgumentException("존재하지않는 음식입니다.");
            }

            int quantity = orderFoods.get(i).getQuantity();
            totalPrice += food.get().getPrice() * quantity;
            int foodPrice = food.get().getPrice() * quantity;

            OrderDetails orderDetails = new OrderDetails(quantity, food.get().getName(), foodPrice);
            orderDetailsRepository.save(orderDetails); // db저장
            orderDetailsList1.add(orderDetails);


            OrderFoodResponseDto orderFoodResponseDto = new OrderFoodResponseDto(food.get().getName(), quantity, foodPrice);
            foods.add(orderFoodResponseDto);
        }

        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("최소주문금액 이상 주문해 주세요");
        }

        int deliveryFee = restaurant.getDeliveryFee();
        totalPrice += deliveryFee;

        Order order = new Order(totalPrice, deliveryFee, restaurant, orderDetailsList1);
        orderRepository.save(order);

        return new OrderResponseDto(restaurant.getName(), totalPrice, deliveryFee, foods);

    }

    public List<OrderResponseDto> readOrders() {
        List<Order> orderList = orderRepository.findAll();

        List<OrderFoodResponseDto> orderFoodResponseDtoList = new ArrayList<>();
        List<OrderResponseDto> foods = new ArrayList<>();


        for (Order order : orderList) {
            List<OrderDetails> orders = order.getOrders();
            for (OrderDetails orderDetails : orders) {
                OrderFoodResponseDto orderFoodResponseDto = new OrderFoodResponseDto(orderDetails.getFoodName(), orderDetails.getQuantity(), orderDetails.getFoodPrice());
                orderFoodResponseDtoList.add(orderFoodResponseDto);
            }
            Restaurant restaurant = order.getRestaurant();
            OrderResponseDto orderResponseDto = new OrderResponseDto(order.getRestaurant().getName(), order.getTotalPrice(), restaurant.getDeliveryFee(), orderFoodResponseDtoList);
            foods.add(orderResponseDto);
        }
        return foods;

    }
}
