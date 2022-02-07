package com.example.reataurant.service;

import com.example.reataurant.dto.DetailsRequestDto;
import com.example.reataurant.model.Food;
import com.example.reataurant.model.OrderDetails;
import com.example.reataurant.model.Orders;
import com.example.reataurant.model.Restaurant;
import com.example.reataurant.repository.FoodRepository;
import com.example.reataurant.repository.OrderDetailsRepository;
import com.example.reataurant.repository.OrderRepository;
import com.example.reataurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//https://www.vbflash.net/45
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository, FoodRepository foodRepository, OrderDetailsRepository orderDetailsRepository){
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public Orders addOrders(List<DetailsRequestDto> detailsRequestDtos, Long restaurantId) throws Exception {

        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(!restaurant.isPresent()){
            throw new Exception("등록되지 않은 음식점 입니다.");
        }
        int totalPrice = 0;

        List <OrderDetails> orderDetailsList = new ArrayList<>(); // 데이터 입력 리스트

        for (DetailsRequestDto order : detailsRequestDtos) {
            Optional<Food> food = foodRepository.findOneByRestaurantIdAndId(restaurantId, order.getId());
            if (!food.isPresent()) {
                throw new Exception("등록되지 않은 메뉴 입니다.");
            }

            String foodName = food.get().getName();
            Long quantity = order.getQuantity();
            if (order.getQuantity() < 1 || order.getQuantity() > 100) {
                throw new Exception("주문 가능 수량을 초과합니다.");
            }

            Long price = order.getQuantity() * food.get().getPrice();
            totalPrice += price;

            OrderDetails orderDetail = new OrderDetails(foodName,quantity, price);
            orderDetailsRepository.save(orderDetail);
            orderDetailsList.add(orderDetail);

        }
        if(totalPrice < restaurant.get().getMinOrderPrice()){
            throw new Exception("최소 주문가격을 넘지 않습니다.");
        }

        totalPrice += restaurant.get().getDeliveryFee();

        Orders orders = new Orders(restaurant.get().getName(), orderDetailsList,
                restaurant.get().getDeliveryFee(), totalPrice);

        orderRepository.save(orders);

        return orders;
    }

    @Transactional
    public List<Orders> getOrders() {
        return orderRepository.findAll();
    }
}
