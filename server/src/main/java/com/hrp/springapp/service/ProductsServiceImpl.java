package com.hrp.springapp.service;

import com.hrp.springapp.model.Product;
import com.hrp.springapp.model.Products;
import com.hrp.springapp.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public void save(Products products) {
        productsRepository.save(products);
    }

    @Override
    public void removeByProduct(Product product) {
        Optional<Products> found = Optional.empty();
        for (Products p : productsRepository.findAll()) {
            if (p.getProductList().contains(product)) {
                found = Optional.of(p);
                break;
            }
        }
        if (found.isPresent()) {
            Products tempP = found.get();
            productsRepository.delete(tempP);
            tempP.getProductList().remove(product);
            productsRepository.save(tempP);
        }
    }

    @Override
    public List<Products> findById(Long id) {
        return productsRepository.findAll().stream().filter(p ->
                p.getUserId().equals(1L)).collect(Collectors.toList());
    }

    @Override
    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public void deleteAll() {
        productsRepository.deleteAll();
    }
}
