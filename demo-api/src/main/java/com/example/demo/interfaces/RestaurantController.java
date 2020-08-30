package com.example.demo.interfaces;

import com.example.demo.interfaces.domain.MenuItem;
import com.example.demo.interfaces.domain.MenuItemRepository;
import com.example.demo.interfaces.domain.Restaurant;
import com.example.demo.interfaces.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> restaurant(){
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantRepository.findById(id);

        List<MenuItem> menuItems = menuItemRepository.findAllByResturantId(id);
        restaurant.setMenuItems(menuItems);

        return restaurant;
    }
}
