package com.example.reataurant.validator;

import com.example.reataurant.dto.RestaurantRequestDto;
import com.example.reataurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantValidator {

    private static RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantValidator(RestaurantRepository restaurantRepository){
        RestaurantValidator.restaurantRepository = restaurantRepository;
    }

    public static void validateRestaurant(RestaurantRequestDto requestDto) throws Exception {
        Long minOrderPrice = requestDto.getMinOrderPrice();
        Long deliveryFee = requestDto.getDeliveryFee();
// 주문 가격 및 배달비 확인
        if(minOrderPrice < 1000 || minOrderPrice > 100000){
            throw new Exception("최소 주문가격은 1000원 이상 100,000원 이하 입니다.");
        }else if(minOrderPrice%100 != 0){
            throw new Exception("최소 주문가격은 100원 단위로 입력할 수 있습니다. ");
        }else if(deliveryFee < 0 || deliveryFee > 10000){
            throw new Exception("기본 배달비는 0원 이상 10,000원 이하 입니다. ");
        }else if(deliveryFee%500 !=0){
            throw new Exception("기본 배달비는 500원 단위로 입력 할 수 있습니다.");
        }
    }
}
