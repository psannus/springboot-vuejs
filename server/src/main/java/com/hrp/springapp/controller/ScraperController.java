package com.hrp.springapp.controller;

import com.hrp.springapp.jwt.AuthenticationService;
import com.hrp.springapp.model.Categories;
import com.hrp.springapp.model.Category;
import com.hrp.springapp.model.Products;
import com.hrp.springapp.service.CategoriesScraperService;
import com.hrp.springapp.service.ProductsScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class ScraperController {

    @Autowired
    private ProductsScraperService productsScraperService;

    @Autowired
    private CategoriesScraperService categoriesScraperService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/categories-get")
    @ResponseBody
    public Categories getCategories(@RequestBody Category category, HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {
        HashMap<String, String> authResult = authenticationService.checkAuthentication(request);
        if (authResult != null) {
            response.setStatus(200);
            return categoriesScraperService.scrapeCategory(category);
        }
        response.setStatus(403);
        return null;
    }

    @PostMapping("/products-list")
    @ResponseBody
    public Products getListOfProducts(@RequestBody Category category, HttpServletRequest request,
                                      HttpServletResponse response) throws IOException {
        HashMap<String, String> authResult = authenticationService.checkAuthentication(request);
        if (authResult != null) {
            response.setStatus(200);
            return productsScraperService.scrapeListOfProducts(category, authResult.get("userId"));
        }
        response.setStatus(403);
        return null;
    }

    @GetMapping("/products-mock")
    public ResponseEntity getAllProductsMock(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, String> authResult = authenticationService.checkAuthentication(request);
        if (authResult != null) {
            response.setStatus(200);
            return productsScraperService.getMockProducts();
        }
        response.setStatus(403);
        return null;
    }
}
