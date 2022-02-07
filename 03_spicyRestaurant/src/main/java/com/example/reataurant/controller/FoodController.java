package com.example.reataurant.controller;

import com.example.reataurant.dto.FoodDto;
import com.example.reataurant.model.Food;
import com.example.reataurant.repository.FoodRepository;
import com.example.reataurant.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodRepository foodRepository;
    private final FoodService foodService;


    //https://stackoverflow.com/questions/51317432/post-a-json-array-into-spring-boot-2-jpa
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public List<FoodDto> createFood(@RequestBody List <FoodDto> foodDtos,
                                    @PathVariable(value = "restaurantId") Long restaurantId)
            throws Exception {

        List<String> foodNames= new ArrayList<String>();
        for(FoodDto foodDto : foodDtos){
            foodNames.add(foodDto.getName());
        }

        // 리스트 내 중복값 체크 https://doing7.tistory.com/145
       if(foodNames.size() != foodNames.stream().distinct().count()){
            throw new Exception("중복되는 메뉴가 존재합니다.");
        }

        for  (FoodDto foodDto : foodDtos) {
            int found = foodService.createFood(foodDto, restaurantId);
                if(found != 0){
                    break;
                }
            }
            return null;
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFood(@PathVariable(value = "restaurantId") Long restaurantId){
        return foodService.getFood(restaurantId);
    }

}