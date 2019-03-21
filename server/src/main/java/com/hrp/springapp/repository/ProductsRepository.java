package com.hrp.springapp.repository;

import com.hrp.springapp.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductsRepository extends JpaRepository<Products, Long> {
}