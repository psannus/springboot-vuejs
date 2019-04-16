package com.hrp.springapp.controller;

import com.hrp.springapp.jwt.JwtAuthenticationFilter;
import com.hrp.springapp.model.Product;
import com.hrp.springapp.model.Products;
import com.hrp.springapp.service.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private ProductsServiceImpl productsService;

    @PostMapping("/basket-save")
    @ResponseBody
    public void saveProducts(@RequestBody Products products, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length == 2
                && jwtAuthenticationFilter.validateToken(cookies[0].getValue(), Long.parseLong(cookies[1].getValue()))) {
            productsService.save(products);
            response.setStatus(200);
        } else {
            response.setStatus(403);
        }
    }

    @RequestMapping("/basket-remove")
    @ResponseBody
    public void removeProduct(@RequestBody Product product, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length == 2
                && jwtAuthenticationFilter.validateToken(cookies[0].getValue(), Long.parseLong(cookies[1].getValue()))) {
            productsService.removeByProduct(product);
            response.setStatus(200);
        } else {
            response.setStatus(403);
        }
    }

    @RequestMapping("/basket-list")
    @ResponseBody
    public List<Products> getBasketList(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length == 2
                && jwtAuthenticationFilter.validateToken(cookies[0].getValue(), Long.parseLong(cookies[1].getValue()))) {
            response.setStatus(200);
            return productsService.findById(Long.parseLong(cookies[1].getValue()));
        }
        response.setStatus(403);
        return null;
    }

    @GetMapping("/products")
    @ResponseBody
    public List<Products> getAllProducts(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length == 2
                && jwtAuthenticationFilter.validateToken(cookies[0].getValue(), Long.parseLong(cookies[1].getValue()))) {
            response.setStatus(200);
            return productsService.findAll();
        }
        response.setStatus(403);
        return null;
    }

    @GetMapping("/products-delete-all")
    @ResponseBody
    public void deleteAllCategories(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length == 2
                && jwtAuthenticationFilter.validateToken(cookies[0].getValue(), Long.parseLong(cookies[1].getValue()))) {
            response.setStatus(200);
            productsService.deleteAll();
        } else {
            response.setStatus(403);
        }
    }
}