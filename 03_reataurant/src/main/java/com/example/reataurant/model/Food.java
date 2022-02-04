package com.example.reataurant.model;

import com.example.reataurant.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Food{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;


    // create food
    public Food(FoodDto requestDto, Long restaurantId){
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurantId = restaurantId;

    }



}
