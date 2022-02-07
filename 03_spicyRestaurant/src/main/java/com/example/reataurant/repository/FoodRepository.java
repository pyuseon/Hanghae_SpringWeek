package com.example.reataurant.repository;

import com.example.reataurant.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List <Food> findByRestaurantIdAndName(Long restaurantId, String name);
    List <Food> findAllByRestaurantId(Long restaurantId);
    Optional<Food> findOneByRestaurantIdAndId(Long restaurantId, Long id);}