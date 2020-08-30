package com.example.demo.interfaces.domain;

import java.util.ArrayList;
import java.util.List;

public class MenuItemRepositoryImpl implements MenuItemRepository{

    private List<MenuItem> menuItems = new ArrayList<>();

    public MenuItemRepositoryImpl() {
        menuItems.add(new MenuItem("kimchi"));
    }

    @Override
    public List<MenuItem> findAllByResturantId(Long id) {
        return menuItems;
    }
}