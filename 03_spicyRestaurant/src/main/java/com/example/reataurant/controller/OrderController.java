package com.example.reataurant.controller;


import com.example.reataurant.dto.DetailsRequestDto;
import com.example.reataurant.dto.OrderRequestDto;
import com.example.reataurant.dto.OrderResponseDto;
import com.example.reataurant.model.Orders;
import com.example.reataurant.service.OrderService;
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

//    @Autowired
//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }


    @PostMapping("/order/request")
    public OrderResponseDto addOrders(@RequestBody OrderRequestDto requestDto) throws Exception {
        {
            Long restaurantId = requestDto.getRestaurantId();
            List<DetailsRequestDto> orderListDto = requestDto.getFoods();
            return  orderService.addOrders(orderListDto, restaurantId);
        }
    }

    @GetMapping("/orders")
    public List<Orders> getOrders()
    {
        return orderService.getOrders();
    }
}
