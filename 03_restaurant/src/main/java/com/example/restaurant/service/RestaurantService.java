package com.example.restaurant.service;

import com.example.restaurant.dto.RestaurantRequestDto;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.RestaurantRepository;
import com.example.restaurant.validator.RestaurantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant createRestaurant(RestaurantRequestDto requestDto) throws Exception {
        // 음식점 등록 유효성 검사
        RestaurantValidator.validateRestaurant(requestDto);
        Restaurant restaurant = new Restaurant(requestDto);
        // 음식점 저장
        restaurantRepository.save(restaurant);
        return restaurant;
    }
}
