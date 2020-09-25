package com.example.demo.application;

import com.example.demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository, MenuRepository menuRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.menuRepository = menuRepository;
        this.reviewRepository = reviewRepository;
    }

    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException(id));

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);

        restaurant.setMenuItems(menuItems);

        List<Review> reviews = reviewRepository.findAllByRestaurantId(id);

        restaurant.setReviews(reviews);

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

    @Transactional
    public Restaurant updateRestaurant(long id, String name, String address) {

        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        restaurant.updateInformation(name, address);

        return restaurant;
    }
}
