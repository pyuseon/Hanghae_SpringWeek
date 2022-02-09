package com.example.restaurant.service;

import com.example.restaurant.dto.RestaurantRequestDto;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.RestaurantRepository;
import com.example.restaurant.validator.RestaurantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant createRestaurant(RestaurantRequestDto requestDto) throws Exception {
        RestaurantValidator.validateRestaurant(requestDto);

        Restaurant restaurant = new Restaurant(requestDto);
        restaurantRepository.save(restaurant);

        return restaurant;
    }

    public List<Restaurant> getRestaurant(Long x, Long y){
       List <Restaurant> restaurantList = restaurantRepository.findAll();
       List <Restaurant> resultList = new ArrayList<>();
        long userX = x;
        long userY = y;

       for(Restaurant restaurant : restaurantList){
          Long targetX = restaurant.getX();
          Long targetY = restaurant.getY();

          long distance = Math.abs(userX-targetX) + Math.abs(userY-targetY);

          if (distance < 4){
              resultList.add(restaurant);
          }
       }
       return resultList;
    }
}
