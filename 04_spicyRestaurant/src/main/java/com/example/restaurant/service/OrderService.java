package com.example.restaurant.service;

import com.example.restaurant.dto.DetailsRequestDto;
import com.example.restaurant.model.Food;
import com.example.restaurant.model.OrderDetails;
import com.example.restaurant.model.Orders;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.FoodRepository;
import com.example.restaurant.repository.OrderDetailsRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.RestaurantRepository;
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

    // 주문 목록 저장하기
    public Orders addOrders(List<DetailsRequestDto> detailsRequestDtos, Long restaurantId,
                            Long x , Long y) throws Exception {

        // 음식점 확인하기
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(!restaurant.isPresent()){
            throw new Exception("등록되지 않은 음식점 입니다.");
        }

        int totalPrice = 0;

        List <OrderDetails> orderDetailsList = new ArrayList<>(); // 데이터 입력 리스트

        for (DetailsRequestDto order : detailsRequestDtos) {

            // 메뉴 확인하기
            Optional<Food> food = foodRepository.findOneByRestaurantIdAndId(restaurantId, order.getId());
            if (!food.isPresent()) {
                throw new Exception("등록되지 않은 메뉴 입니다.");
            }

            // 주문 가능 수량 체크
            String foodName = food.get().getName();
            Long quantity = order.getQuantity();
            if (order.getQuantity() < 1 || order.getQuantity() > 100) {
                throw new Exception("주문 가능 수량을 초과합니다.");
            }

            // 주문 음식 가격 계산 & 총 가격에 음식 가격 더하기
            long price = order.getQuantity() * food.get().getPrice();
            totalPrice += price;

            // 주문 음식 저장하기
            OrderDetails orderDetail = new OrderDetails(foodName,quantity, price);
            orderDetailsRepository.save(orderDetail);
            orderDetailsList.add(orderDetail);

        }

        // 최소 주문가격 체크
        if(totalPrice < restaurant.get().getMinOrderPrice()){
            throw new Exception("최소 주문가격을 넘지 않습니다.");
        }

        // 거리에 따른 배달비 산정 1km 당 500원 추가
        long targetX = restaurant.get().getX();
        long targetY = restaurant.get().getY();
        long distance = Math.abs(x-targetX) + Math.abs(y-targetY);

        totalPrice += (restaurant.get().getDeliveryFee() + 500*distance);

        // order에 주문 내역 추가 및 저장
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
