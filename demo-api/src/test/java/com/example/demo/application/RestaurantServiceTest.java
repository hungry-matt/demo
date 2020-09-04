package com.example.demo.application;

import com.example.demo.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    private RestaurantRepository restaurantRepository;

    private MenuItemRepository menuItemRepository;

    private MenuRepository menuRepository;

    @Before
    public void setUp() {
        menuItemRepository = new MenuItemRepositoryImpl();
        restaurantRepository = new RestaurantRepositoryImpl();
        menuRepository = new MenuRepositoryImpl();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository, menuRepository);
    }

    @Test
    public void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getId(), is(1004L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertThat(menuItem.getName(), is("kimchi"));
    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void getMenu() {
        List<Menu> menuList = restaurantService.getMenus();

        Menu menu = menuList.get(0);

        assertThat(menu.getName(), is("chicken"));
        assertThat(menu.getPrice(), is(5000));

    }

}