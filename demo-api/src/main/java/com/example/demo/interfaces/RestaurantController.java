package com.example.demo.interfaces;

import com.example.demo.application.RestaurantService;
import com.example.demo.domain.MenuItem;
import com.example.demo.domain.MenuItemRepository;
import com.example.demo.domain.Restaurant;
import com.example.demo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> restaurant(){
        List<Restaurant> restaurants =  restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {

        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }
}
