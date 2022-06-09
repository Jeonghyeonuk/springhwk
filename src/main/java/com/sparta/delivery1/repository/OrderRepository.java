package com.sparta.delivery1.repository;

import com.sparta.delivery1.domain.Order;
import com.sparta.delivery1.dto.Order.OrderResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
}




