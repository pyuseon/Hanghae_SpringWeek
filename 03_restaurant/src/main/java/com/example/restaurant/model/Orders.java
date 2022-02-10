package com.example.restaurant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Orders {
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 음식점 이름
    @Column(nullable = false)
    private String restaurantName;

    // 주문 메뉴
    @OneToMany
    @JoinColumn(name = "ORDER_ID")
    private List<OrderDetails> foods;

    // 배달비
    @Column(nullable = false)
    private Long deliveryFee;

    // 총 금액
    @Column(nullable=false)
    private int totalPrice;

    public Orders(String restaurantname, List<OrderDetails> foods,
                  Long deliveryPrice, Integer totalPrice) {
        this.restaurantName = restaurantname;
        this.foods = foods;
        this.deliveryFee = deliveryPrice;
        this.totalPrice = totalPrice;
    }
}
