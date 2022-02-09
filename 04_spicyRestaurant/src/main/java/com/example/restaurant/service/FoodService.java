package com.example.restaurant.service;

import com.example.restaurant.dto.FoodRequestDto;
import com.example.restaurant.model.Food;
import com.example.restaurant.repository.FoodRepository;
import com.example.restaurant.validator.FoodValidator;
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

    public List<FoodRequestDto> createFood(List<FoodRequestDto> requestDtos, Long restaurantId)
            throws Exception {

        // 입력 음식명에 중복된 음식점 에러 확인
        FoodValidator.dupFood(requestDtos);

        // 기존 저장된 음식점 명과 중복
        for (FoodRequestDto requestDto : requestDtos) {
            String name = requestDto.getName();
            List<Food> found = foodRepository.findByRestaurantIdAndName(restaurantId,name);

            if(found.size() != 0){
                throw new Exception("중복되는 메뉴가 존재합니다.");
            }
            else{
                // 음식 가격 에러 검사 (범위 / 단위)
                FoodValidator.validateFood(requestDto);
                Food food = new Food(requestDto, restaurantId);
                foodRepository.save(food);
            }
        }
        return requestDtos;
    }

    public List<Food> getFood(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }
}
