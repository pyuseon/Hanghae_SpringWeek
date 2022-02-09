package com.example.restaurant.controller;

import com.example.restaurant.dto.RestaurantRequestDto;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.RestaurantRepository;
import com.example.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/restaurant/register")
    public Restaurant createRestaurant(@RequestBody RestaurantRequestDto requestDto) throws Exception {
        Restaurant restaurant = restaurantService.createRestaurant(requestDto);
        return restaurant;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(@RequestParam("x") Long x,
                                           @RequestParam("y") Long y){
        return restaurantService.getRestaurant(x, y);
    }

}
