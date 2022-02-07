package com.example.reataurant.controller;

import com.example.reataurant.dto.RestaurantDto;
import com.example.reataurant.model.Restaurant;
import com.example.reataurant.repository.RestaurantRepository;
import com.example.reataurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/register")
    public Restaurant createRestaurant(@RequestBody RestaurantDto requestDto) throws Exception {

        Restaurant restaurant = restaurantService.createRestaurant(requestDto);

        return restaurant;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(){

        return restaurantRepository.findAll();
    }

}
