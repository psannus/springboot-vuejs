package com.hrp.springapp.controller;

import com.hrp.springapp.model.Category;
import com.hrp.springapp.service.CategoriesScraperService;
import com.hrp.springapp.service.ProductsScraperService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScraperServiceTest {

    @Autowired
    private CategoriesScraperService categoriesScraperService;

    @Autowired
    private ProductsScraperService productsScraperService;

    @Test
    public void contexLoads() {
        assert categoriesScraperService != null && productsScraperService != null;
    }

    @Test
    public void getCategories() throws IOException {
        Category initial = new Category();
        initial.setId(1L);
        initial.setName("init");
        initial.setUrl("https://www.prismamarket.ee/products/selection");
        assert categoriesScraperService.scrapeCategory(initial).getCategoryList().size() == 20;
    }

    @Test
    public void getProductsByCategory() throws IOException {
        Category init = new Category();
        init.setId(1L);
        init.setUrl("https://www.prismamarket.ee/products/17180");
        init.setName("Rapsiõlid");

        assert productsScraperService.scrapeListOfProducts(init, "1").getProductList().size() != 0;
    }
}