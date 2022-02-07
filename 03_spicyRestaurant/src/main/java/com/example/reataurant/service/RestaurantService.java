package com.example.reataurant.service;

import com.example.reataurant.dto.RestaurantDto;
import com.example.reataurant.model.Restaurant;
import com.example.reataurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant createRestaurant(RestaurantDto requestDto) throws Exception {
        String name = requestDto.getName();
        Long minOrderPrice = requestDto.getMinOrderPrice();
        Long deliveryFee = requestDto.getDeliveryFee();

        if(minOrderPrice < 1000 || minOrderPrice > 100000){
            throw new Exception("최소 주문가격은 1000원 이상 100,000원 이하 입니다.");
        }else if(minOrderPrice%100 != 0){
            throw new Exception("최소 주문가격은 100원 단위로 입력할 수 있습니다. ");
        }else if(deliveryFee < 0 || deliveryFee > 10000){
            throw new Exception("기본 배달비는 0원 이상 10,000원 이하 입니다. ");
        }else if(deliveryFee%500 !=0){
            throw new Exception("기본 배달비는 500원 단위로 입력 할 수 있습니다.");
        }

        Restaurant restaurant = new Restaurant(requestDto);
        restaurantRepository.save(restaurant);

        return restaurant;
    }
}
