package com.example.restaurant.model;

import com.example.restaurant.dto.RestaurantRequestDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Restaurant{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 음식점 이름
    @Column(nullable = false)
    private String name;

    // 최소 주문가격
    @Column(nullable = false)
    private Long minOrderPrice;

    // 배달료
    @Column(nullable = false)
    private Long deliveryFee;

    // 음식점 메뉴 리스트
    @JsonManagedReference // 순환 참조를 막기 위한 어노테이션
    @OneToMany(mappedBy = "restaurant")
    List<Food> foods;

    public Restaurant(RestaurantRequestDto requestDto){
        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }

}
