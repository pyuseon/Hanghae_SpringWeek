package com.example.restaurant.controller;

import com.example.restaurant.dto.FoodRequestDto;
import com.example.restaurant.model.Food;
import com.example.restaurant.repository.FoodRepository;
import com.example.restaurant.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodRepository foodRepository;
    private final FoodService foodService;


    //https://stackoverflow.com/questions/51317432/post-a-json-array-into-spring-boot-2-jpa
    @Secured("ROLE_ADMIN")
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public List<FoodRequestDto> createFood(@RequestBody List<FoodRequestDto> requestDtos,
                                           @PathVariable(value = "restaurantId") Long restaurantId)
            throws Exception {
        foodService.createFood(requestDtos, restaurantId);
        return null;
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFood(@PathVariable(value = "restaurantId") Long restaurantId){
        return foodService.getFood(restaurantId);
    }

}