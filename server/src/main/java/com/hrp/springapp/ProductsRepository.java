package com.hrp.springapp;

import com.hrp.springapp.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface ProductsRepository extends JpaRepository<Products, Long> {
}