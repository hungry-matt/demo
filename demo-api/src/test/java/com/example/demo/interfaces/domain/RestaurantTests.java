package com.example.demo.interfaces.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantTests {

    @Test
    public void creation() {
        Restaurant restaurant = new Restaurant("Bob zip");

        assertThat(restaurant.getName(), is("Bob zip"));
    }

    @Test
    public void information() {
        Restaurant restaurant = new Restaurant("Bob zip", "Seoul");
        assertThat(restaurant.getAddress(), is("Seoul"));
        assertThat(restaurant.getInformation(), is("Bob zip in Seoul"));
    }

}