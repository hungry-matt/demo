package com.example.demo.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RestaurantRepositoryImplTest {

    @Test
    public void save() {
        RestaurantRepository repository = new RestaurantRepositoryImpl();

        int oldCount = repository.findAll().size();

        Restaurant restaurant = new Restaurant("name", "address");
        repository.save(restaurant);

        assertThat(restaurant.getId(), is(1234L));

        int newCount = repository.findAll().size();

        assertThat(newCount - oldCount, is(1));
    }
}