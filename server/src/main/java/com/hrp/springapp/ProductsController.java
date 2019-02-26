package com.hrp.springapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping("/products")
    @ResponseBody
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    @GetMapping("/products-mock")
    public ResponseEntity getAllProductsMock() throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(
                new String(Files.readAllBytes(Paths.get("~/../db.json")), StandardCharsets.UTF_8),
                httpHeaders,
                HttpStatus.OK
        );
    }

}