package com.example.reataurant.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class OrderResponseDto {
    private String restaurantName;
    private List<DetailsResponseDto> foods;
    private Long deliveryFee;
    private Integer totalPrice;


    @Builder
    public OrderResponseDto(String restaurantName, List<DetailsResponseDto> foods,
                            Long deliveryFee, Integer totalPrice){
        this.restaurantName = restaurantName;
        this.foods = foods;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }


}
