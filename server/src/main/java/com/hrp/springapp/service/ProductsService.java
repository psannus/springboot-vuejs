package com.hrp.springapp.service;

import com.hrp.springapp.model.Product;
import com.hrp.springapp.model.Products;

import java.util.List;

public interface ProductsService {
    void save(Products products);

    void updateByProduct(Product product);
    void removeByProduct(Product product);
    List<Products> findById(Long id);
    List<Products> findAll();
    void deleteAll();

}
