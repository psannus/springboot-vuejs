package com.hrp.springapp.controller;

import com.hrp.springapp.jwt.AuthenticationService;
import com.hrp.springapp.model.Product;
import com.hrp.springapp.model.Products;
import com.hrp.springapp.service.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    private ProductsServiceImpl productsService;
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/basket-save")
    @ResponseBody
    public void saveProducts(@RequestBody Products products, HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> authResult = authenticationService.checkAuthentication(request);
        if (authResult != null) {
            response.setStatus(200);
            products.setUserId(Long.parseLong(authResult.get("userId")));
            productsService.save(products);
        } else {
            response.setStatus(403);
        }
    }

    @RequestMapping("/basket-remove")
    @ResponseBody
    public void removeProduct(@RequestBody Product product, HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> authResult = authenticationService.checkAuthentication(request);
        if (authResult != null) {
            response.setStatus(200);
            productsService.removeByProduct(product);
        } else {
            response.setStatus(403);
        }
    }

    @RequestMapping("/basket-list")
    @ResponseBody
    public List<Products> getBasketList(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> authResult = authenticationService.checkAuthentication(request);
        if (authResult != null) {
            response.setStatus(200);
            return productsService.findById(Long.parseLong(authResult.get("userId")));
        } else {
            response.setStatus(403);
            return null;
        }
    }

    @GetMapping("/products")
    @ResponseBody
    public List<Products> getAllProducts(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> authResult = authenticationService.checkAuthentication(request);
        if (authResult != null) {
            response.setStatus(200);
            return productsService.findAll();
        } else {
            response.setStatus(403);
            return null;
        }
    }

    @GetMapping("/products-delete-all")
    @ResponseBody
    public void deleteAllCategories(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> authResult = authenticationService.checkAuthentication(request);
        if (authResult != null) {
            response.setStatus(200);
            productsService.deleteAll();
        } else {
            response.setStatus(403);
        }
    }
}