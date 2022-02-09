package com.example.reataurant.controller;

import com.example.reataurant.dto.FoodRequestDto;
import com.example.reataurant.model.Food;
import com.example.reataurant.repository.FoodRepository;
import com.example.reataurant.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodRepository foodRepository;
    private final FoodService foodService;


    //https://stackoverflow.com/questions/51317432/post-a-json-array-into-spring-boot-2-jpa
    // 음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public List<FoodRequestDto> createFood(@RequestBody List<FoodRequestDto> requestDtos,
                                           @PathVariable(value = "restaurantId") Long restaurantId) throws Exception {
        foodService.createFood(requestDtos, restaurantId);
        return null;
    }

    // 음식 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFood(@PathVariable(value = "restaurantId") Long restaurantId) {
        return foodService.getFood(restaurantId);
    }

}