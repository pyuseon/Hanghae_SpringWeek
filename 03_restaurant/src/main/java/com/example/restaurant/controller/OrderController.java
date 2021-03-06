package com.example.restaurant.controller;


import com.example.restaurant.dto.DetailsRequestDto;
import com.example.restaurant.dto.OrderRequestDto;
import com.example.restaurant.model.Orders;
import com.example.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문하기
    @PostMapping("/order/request")
    public Orders addOrders(@RequestBody OrderRequestDto requestDto) throws Exception {
        {
            Long restaurantId = requestDto.getRestaurantId();
            List<DetailsRequestDto> orderListDto = requestDto.getFoods();
            return orderService.addOrders(orderListDto, restaurantId);
        }
    }

    //주문조회
    @GetMapping("/orders")
    public List<Orders> getOrders()
    {
        return orderService.getOrders();
    }
}
