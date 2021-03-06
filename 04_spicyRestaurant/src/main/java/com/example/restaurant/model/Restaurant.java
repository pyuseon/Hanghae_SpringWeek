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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long minOrderPrice;

    @Column(nullable = false)
    private Long deliveryFee;

    @Column(nullable = false)
    private Long x;

    @Column(nullable = false)
    private Long y;

    @JsonManagedReference
    @OneToMany(mappedBy = "restaurant")
    List<Food> foods;

    public Restaurant(RestaurantRequestDto requestDto){
        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
        this.x = requestDto.getX();
        this.y = requestDto.getY();
    }

}
