package com.sparta.delivery1.repository;

import com.sparta.delivery1.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository  extends JpaRepository<Restaurant, Long> {
}
