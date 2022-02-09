package com.example.restaurant.controller;


import com.example.restaurant.dto.DetailsRequestDto;
import com.example.restaurant.dto.OrderRequestDto;
import com.example.restaurant.model.Orders;
import com.example.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/request")
    public Orders addOrders(@RequestBody OrderRequestDto requestDto,
                            @RequestParam("x") Long x,
                            @RequestParam("y") Long y) throws Exception {
        {
            Long restaurantId = requestDto.getRestaurantId();
            List<DetailsRequestDto> orderListDto = requestDto.getFoods();
            return orderService.addOrders(orderListDto, restaurantId, x, y);
        }
    }

    @GetMapping("/orders")
    public List<Orders> getOrders()
    {
        return orderService.getOrders();
    }
}
