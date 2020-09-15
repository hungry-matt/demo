package com.example.demo.application;

import com.example.demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private MenuRepository menuRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.menuRepository = menuRepository;
    }

    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);

        restaurant.setMenuItems(menuItems);

        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
         List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants;
    }

    public List<Menu> getMenus() {
        List<Menu> menuList = menuRepository.findAllMenu();
        return menuList;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
