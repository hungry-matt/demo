package com.example.demo.application;

import com.example.demo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private MenuRepository menuRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();

        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository, menuRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
        restaurant.addMenuItem(new MenuItem("kimchi"));
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

        List<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu("chicken", 5000));

        given(menuRepository.findAllMenu()).willReturn(menuList);
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

    @Test
    public void addRestaurant() {
        Restaurant restaurant = new Restaurant("name", "address");
        Restaurant saved = new Restaurant(1234L, "Joker", "Seoul");

        given(restaurantRepository.save(any())).willReturn(saved);

        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId(), is(1234L));
    }

}