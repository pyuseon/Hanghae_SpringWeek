package com.example.reataurant.service;

import com.example.reataurant.dto.FoodDto;
import com.example.reataurant.model.Food;
import com.example.reataurant.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }

    public Food createFood(FoodDto requestDto, Long restaurantId) throws Exception {
        String name = requestDto.getName();
        Long price = requestDto.getPrice();
        List<Food> found = foodRepository.findByRestaurantIdAndName(restaurantId,name);

        if(found.size() != 0){
            throw new Exception("중복되는 메뉴가 존재합니다.");
        }else if(price <100 || price >1000000) {
            throw new Exception("메뉴 가격은 100원 이상 100만원 이하로 입력해주시길 바랍니다.");
        }else if(price%100 != 0){
            throw new Exception("100원 단위로 입력해주세요.");
        }

        Food food = new Food(requestDto, restaurantId);
        foodRepository.save(food);

        return food;
    }

    public List<Food> getFood(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }
}
