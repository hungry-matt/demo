package com.example.demo.application;

import com.example.demo.domain.Category;
import com.example.demo.domain.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
    @Mock
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        categoryService = new CategoryService(categoryRepository);

        List<Category> categories = new ArrayList<>();

        categories.add(Category.builder()
                .name("Korean Food")
                .build());

        given(categoryService.getCategories()).willReturn(categories);
    }

    @Test
    public void getCategories() {
        List<Category> categories = categoryService.getCategories();

        Category category = categories.get(0);

        assertThat(category.getName(), is("Korean Food"));
    }

    @Test
    public void addCategory() {
        Category category = categoryService.addCategory("Seoul");

        verify(categoryRepository).save(any());

        assertThat(category.getName(), is("Seoul"));
    }
}