package com.example.demo.application;

import com.example.demo.domain.Menu;
import com.example.demo.domain.MenuItem;
import com.example.demo.domain.MenuItemRepository;
import com.example.demo.domain.RestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MenuItemServiceTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    private MenuItemService menuItemService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        menuItemService = new MenuItemService(menuItemRepository);
    }

    @Test
    public void getMenuItems() {
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(Arrays.asList(
                MenuItem.builder()
                .name("test")
                .restaurantId(1004L)
                .build()
        ));

        List<MenuItem> menuItems = menuItemService.getMenuItems(1004L);

        MenuItem menuItem = menuItems.get(0);

        assertThat(menuItem.getRestaurantId(), is(1004L));
    }

    @Test
    public void bulkUpdate() {

        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(MenuItem.builder()
                .name("kimchi")
                .build());

        menuItems.add(MenuItem.builder()
                .name("gukbob")
                .build());

        menuItemService.bulkUpdate(1L, menuItems);

        //times : 인자값 만큼 실행
        verify(menuItemRepository, times(2)).save(any());
    }
}