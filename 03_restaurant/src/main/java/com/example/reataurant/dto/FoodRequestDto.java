package com.example.reataurant.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FoodRequestDto {
    private final String name;
    private final Long price;
}