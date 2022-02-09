package com.example.reataurant.controller;

import com.example.reataurant.dto.RestaurantRequestDto;
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

    // 음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant createRestaurant(@RequestBody RestaurantRequestDto requestDto) throws Exception {
        return  restaurantService.createRestaurant(requestDto);
    }

    // 음식점 조회
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(){

        return restaurantRepository.findAll();
    }

}
