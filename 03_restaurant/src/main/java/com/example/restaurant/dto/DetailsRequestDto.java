package com.example.restaurant.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DetailsRequestDto {
    private Long id;
    private String name;
    private Long price;
    private Long quantity;

    @Builder
    public DetailsRequestDto(String name, Long price, Long quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
