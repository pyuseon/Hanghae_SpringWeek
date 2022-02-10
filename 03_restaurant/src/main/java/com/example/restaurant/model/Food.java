package com.example.restaurant.model;

import com.example.restaurant.dto.FoodRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Food{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 음식명
    @Column(nullable = false)
    private String name;

    // 가격
    @Column(nullable = false)
    private Long price;

    // 해당 음식의 레스토랑 정보
    @JsonBackReference // 순환 참조를 막기 위한 어노테이션
    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    public Food(FoodRequestDto requestDto, Restaurant restaurant){
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurant = restaurant;
    }
}
