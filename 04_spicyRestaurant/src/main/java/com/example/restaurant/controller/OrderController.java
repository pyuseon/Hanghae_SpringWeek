package com.example.restaurant.controller;


import com.example.restaurant.dto.DetailsRequestDto;
import com.example.restaurant.dto.OrderRequestDto;
import com.example.restaurant.model.Orders;
import com.example.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문하기
    @Secured("ROLE_USER")
    @PostMapping("/order/request")
    public Orders addOrders(@RequestBody OrderRequestDto requestDto ) throws Exception {
        {
            Long restaurantId = requestDto.getRestaurantId();
            List<DetailsRequestDto> orderListDto = requestDto.getFoods();
            return orderService.addOrders(orderListDto, restaurantId, requestDto.getX(), requestDto.getY());
        }
    }


    // 주문 목록 가져오기
    @Secured("ROLE_USER")
    @GetMapping("/orders")
    public List<Orders> getOrders()
    {return orderService.getOrders();
    }
}
