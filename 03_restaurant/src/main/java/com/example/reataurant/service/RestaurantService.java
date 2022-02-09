package com.example.reataurant.service;

import com.example.reataurant.dto.RestaurantRequestDto;
import com.example.reataurant.model.Restaurant;
import com.example.reataurant.repository.RestaurantRepository;
import com.example.reataurant.validator.RestaurantValidator;
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
        RestaurantValidator.validateRestaurant(requestDto);
        Restaurant restaurant = new Restaurant(requestDto);
        restaurantRepository.save(restaurant);
        return restaurant;
    }
}
