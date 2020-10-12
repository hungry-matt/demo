package com.example.demo.application;

import com.example.demo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
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

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();

        mockReviewRepository();

        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository, menuRepository, reviewRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .categoryId(1L)
                .build();

        restaurants.add(restaurant);


        List<MenuItem> menuItems = Arrays.asList(MenuItem.builder()
                .restaurantId(1004L)
                .name("kimchi")
                .build());

        given(restaurantRepository.findAllByAddressContainingAndCategoryId("Seoul", 1L)).willReturn(restaurants);

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);

        List<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu("chicken", 5000));

        given(menuRepository.findAllMenu()).willReturn(menuList);

    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();

        reviews.add(Review.builder()
                .restaurantId(1004L)
                .id(1L)
                .name("name")
                .score(5)
                .description("bad")
                .build()
        );

        given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews);

    }

    @Test
    public void getRestaurantWithExisted() {

        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getId(), is(1004L));

        MenuItem menuItem1 = restaurant.getMenuItems().get(0);

        assertThat(menuItem1.getName(), is("kimchi"));

        Review review = restaurant.getReviews().get(0);

        assertThat(review.getDescription(), is("bad"));
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNotExisted() {
        restaurantService.getRestaurant(404L);
    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants("Seoul", 1L);

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
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("name")
                .address("address")
                .build();

        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId(), is(1234L));
    }

    @Test
    public void updateRestaurant() {

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(1004L, "Sool zip", "Busan");

        assertThat(restaurant.getName(), is("Sool zip"));
        assertThat(restaurant.getAddress(), is("Busan"));
    }
}