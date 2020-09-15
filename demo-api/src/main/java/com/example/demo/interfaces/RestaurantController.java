package com.example.demo.interfaces;

import com.example.demo.application.RestaurantService;
import com.example.demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> restaurant() {
        List<Restaurant> restaurants =  restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {

        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }

    @GetMapping("/menu")
    public List<Menu> restaurantsMenu() {
        List<Menu> menuList = restaurantService.getMenus();
        return menuList;
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant resource)
            throws URISyntaxException {
        String name = resource.getName();
        String address = resource.getAddress();

        Restaurant restaurant = new Restaurant(name, address);

        restaurantService.addRestaurant(restaurant);

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }
}
