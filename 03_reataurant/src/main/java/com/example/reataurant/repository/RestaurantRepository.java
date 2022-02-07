package com.example.reataurant.repository;

import com.example.reataurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    List<Restaurant> findAll();
    Optional<Restaurant> findById(Long Id);
}
