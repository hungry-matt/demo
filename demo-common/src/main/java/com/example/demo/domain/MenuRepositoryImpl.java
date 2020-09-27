package com.example.demo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuRepositoryImpl implements MenuRepository{

    private List<Menu> menuList = new ArrayList<>();

    public MenuRepositoryImpl() {
        menuList.add(new Menu("chicken", 5000));
        menuList.add(new Menu("piza", 1000));
    }

    @Override
    public List<Menu> findAllMenu() {
        return this.menuList;
    }
}
