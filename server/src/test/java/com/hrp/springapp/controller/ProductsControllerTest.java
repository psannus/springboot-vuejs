package com.hrp.springapp.controller;

import com.hrp.springapp.model.Category;
import com.hrp.springapp.repository.ProductsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsControllerTest {
    @Autowired
    private ProductsRepository protsRepository;

    private ProductsController productsController = new ProductsController();

    @Test
    ductsRepository;

    private ProductsController productsController = new ProductsController();

    @Test
    public void saveProducts() throws IOException {
        System.out.println(productsRepository);
        int start = (int) productsRepository.findAll().stream().filter(p -> p.getUserId().equals(1L)).count();

        Category init = new Category();
        init.setId(1L);
        init.setUrl("https://www.prismamarket.ee/products/17180");
        init.setName("Rapsiõlid");


        productsRepository.save(productsController.getListOfProducts(init));

        int end = (int) productsRepository.findAll().stream().filter(p -> p.getUserId().equals(1L)).count();
        assert start <= end;
    }

    @Test
    public void deleteAllCategories() {
        int start = (int) productsRepository.findAll().stream().filter(p -> p.getUserId().equals(1L)).count();
        productsRepository.deleteAll();
        int end = (int) productsRepository.findAll().stream().filter(p -> p.getUserId().equals(1L)).count();
        assert end <= start;
    }

    @Test
    public void getListOfProducts() throws IOException {
        Category init = new Category();
        init.setId(1L);
        init.setUrl("https://www.prismamarket.ee/products/17180");
        init.setName("Rapsiõlid");
        assert productsController.getListOfProducts(init).getProductList().size() == 12;
    }
}