package com.example.demo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepositoryImpl() {
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));
        restaurants.add(new Restaurant(2020L, "Cyber food", "Seoul"));
    }

    @Override
    //레스토랑 전체 찾기
    public List<Restaurant> findAll() {
        return restaurants;
    }

    @Override
    //레스토랑 찾기
    public Restaurant findById(Long id) {
        return restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    //레스토랑 추가
    public Restaurant save(Restaurant restaurant) {
        restaurant.setId(1234);
        restaurants.add(restaurant);
        return restaurant;
    }
}
