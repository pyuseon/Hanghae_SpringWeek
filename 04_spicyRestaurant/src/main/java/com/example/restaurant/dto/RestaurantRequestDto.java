package com.example.restaurant.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RestaurantRequestDto {
    private final String name;
    private final Long minOrderPrice;
    private final Long deliveryFee;
    private final Long x;
    private final Long y;


}
