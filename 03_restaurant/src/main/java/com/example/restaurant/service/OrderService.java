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

    public Orders addOrders(List<DetailsRequestDto> detailsRequestDtos, Long restaurantId) throws Exception {

        // 등록된 음식점인지 확인하기
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(!restaurant.isPresent()){
            throw new Exception("등록되지 않은 음식점 입니다.");
        }
        int totalPrice = 0;

        List <OrderDetails> orderDetailsList = new ArrayList<>(); // 데이터 입력 리스트

        for (DetailsRequestDto order : detailsRequestDtos) {

            // 메뉴 등록 여부 확인
            Optional<Food> food = foodRepository.findOneByRestaurantIdAndId(restaurantId, order.getId());
            if (!food.isPresent()) {
                throw new Exception("등록되지 않은 메뉴 입니다.");
            }

            String foodName = food.get().getName();
            Long quantity = order.getQuantity();
            // 주문 가능 수량 확인
            if (order.getQuantity() < 1 || order.getQuantity() > 100) {
                throw new Exception("주문 가능 수량을 초과합니다.");
            }

            // 메뉴당 가격 계산
            long price = order.getQuantity() * food.get().getPrice();

            // 총 가격에 메뉴당 가격 더하기
            totalPrice += price;

            // 주문 상세 내역에 음식명, 수량, 가격 저장하기
            OrderDetails orderDetail = new OrderDetails(foodName,quantity, price);
            orderDetailsRepository.save(orderDetail);

            // 주문 도메인에 저장하기 위한 주문 상세 리스트
            orderDetailsList.add(orderDetail);
        }

        // 최고 주문가격 충족 여부 확인
        if(totalPrice < restaurant.get().getMinOrderPrice()){
            throw new Exception("최소 주문가격을 넘지 않습니다.");
        }


        // 총 주문가격에 배달료 더하기
        totalPrice += restaurant.get().getDeliveryFee();


        // 주문 도메인에 주문 내역 저장하기
        Orders orders = new Orders(restaurant.get().getName(), orderDetailsList,
                restaurant.get().getDeliveryFee(), totalPrice);
        orderRepository.save(orders);
        return orders;
    }

    @Transactional
    public List<Orders> getOrders() {
        // 주문내역 모두 출력하기
        return orderRepository.findAll();
    }
}
