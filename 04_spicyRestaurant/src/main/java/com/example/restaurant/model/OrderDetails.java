package com.example.restaurant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class OrderDetails {
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable=false)
    private Long quantity;

    @Column(nullable=false)
    private Long price;

    @Column(nullable=false)
    private String name;
//
//    @Column(nullable=false)
//    private Long restaurantId;


    // create Order
    public OrderDetails(String name, Long quantity, Long price ){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

}
