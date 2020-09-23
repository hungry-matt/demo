package com.example.demo.application;

import com.example.demo.domain.MenuItem;
import com.example.demo.domain.MenuItemRepository;
import com.example.demo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private MenuItemRepository menuItemRespository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRespository = menuItemRepository;
    }

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems) {
            menuItem.setRestaurantId(restaurantId);
            menuItemRespository.save(menuItem);
        }
    }

}
