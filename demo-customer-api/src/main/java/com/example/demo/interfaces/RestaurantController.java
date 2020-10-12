package com.example.demo.interfaces;

import com.example.demo.application.RestaurantService;
import com.example.demo.domain.Menu;
import com.example.demo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> restaurant(
            @RequestParam("region") String region
            , @RequestParam("category") Long category
    ) {
        List<Restaurant> restaurants =  restaurantService.getRestaurants(region, category);

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
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource)
            throws URISyntaxException {

        Restaurant restaurant = restaurantService.addRestaurant(
                Restaurant.builder()
                    .name(resource.getName())
                    .address(resource.getAddress())
                    .build());

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @RequestBody Restaurant resource) {
        restaurantService.updateRestaurant(id, resource.getName(), resource.getAddress());
        return "{}";
    }
}
