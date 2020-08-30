package com.example.demo.interfaces.domain;

import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findAllByResturantId(Long id);
}
