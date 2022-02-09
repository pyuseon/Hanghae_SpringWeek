package com.example.restaurant.validator;

import com.example.restaurant.dto.FoodRequestDto;
import com.example.restaurant.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FoodValidator {
    private static FoodRepository foodRepository;

    @Autowired
    public FoodValidator(FoodRepository foodRepository){
        FoodValidator.foodRepository = foodRepository;
    }

    public static void validateFood(FoodRequestDto requestDto) throws Exception {
        Long price = requestDto.getPrice();

        if(price <100 || price >1000000) {
            throw new Exception("메뉴 가격은 100원 이상 100만원 이하로 입력해주시길 바랍니다.");
        }else if(price%100 != 0){
            throw new Exception("100원 단위로 입력해주세요.");
        }

    }

    public static void dupFood(List<FoodRequestDto> requestDtos) throws Exception {
        List<String> foodNames= new ArrayList<>();
        for(FoodRequestDto requestDto : requestDtos){
            foodNames.add(requestDto.getName());
        }
        // 등록 음식 리스트 내 중복값 체크 https://doing7.tistory.com/145
        if(foodNames.size() != foodNames.stream().distinct().count()){
            throw new Exception("중복되는 메뉴가 존재합니다.");
        }

    }
}
