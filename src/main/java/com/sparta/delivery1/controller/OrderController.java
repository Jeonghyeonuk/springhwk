package com.sparta.delivery1.controller;

import com.sparta.delivery1.domain.Order;
import com.sparta.delivery1.dto.Order.OrderRequestDto;
import com.sparta.delivery1.dto.Order.OrderResponseDto;
import com.sparta.delivery1.repository.OrderRepository;
import com.sparta.delivery1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {
    private final OrderService orderService;

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/order/request")
    @ResponseBody
    public OrderResponseDto gerOrders(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.getOrders(orderRequestDto);
    }

    @GetMapping("/orders")
    @ResponseBody
    public List<OrderResponseDto> readOrders(){
        return orderService.readOrders();
    }
}
