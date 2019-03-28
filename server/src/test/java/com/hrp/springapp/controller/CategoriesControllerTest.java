package com.hrp.springapp.controller;

import com.hrp.springapp.model.Category;
import org.junit.Test;

import java.io.IOException;

public class CategoriesControllerTest {
    private CategoriesController categoriesController = new CategoriesController();

    @Test
    public void getCategories() throws IOException {
        Category initial = new Category();
        initial.setId(1L);
        initial.setName("init");
        initial.setUrl("https://www.prismamarket.ee/products/selection");
        System.out.println(categoriesController.getCategories(initial).getCategoryList().get(6));
        assert categoriesController.getCategories(initial).getCategoryList().size() == 20;
    }

    @Test
    public void getCategoty() throws IOException {
        Category initial = new Category();
        initial.setId(1L);
        initial.setName("init");
        initial.setUrl("https://www.prismamarket.ee/products/selection");
        Category c = categoriesController.getCategories(initial).getCategoryList().get(6);
        assert c.getName().equals("Toidurasvad ja õlid");
    }
}