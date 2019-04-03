package com.hrp.springapp.controller;

import com.hrp.springapp.jwt.JwtAuthenticationFilter;
import com.hrp.springapp.model.Categories;
import com.hrp.springapp.model.Category;
import com.hrp.springapp.repository.CategoriesRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoriesController {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @GetMapping("/categories")
    @ResponseBody
    public List<Categories> getAllCategories(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length == 2
                && jwtAuthenticationFilter.validateToken(cookies[0].getValue(), Long.parseLong(cookies[1].getValue()))) {
            response.setStatus(200);
            return categoriesRepository.findAll();
        }
        response.setStatus(403);
        return null;
    }

    @GetMapping("/categories-delete-all")
    @ResponseBody
    public void deleteAllCategories(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length == 2
                && jwtAuthenticationFilter.validateToken(cookies[0].getValue(), Long.parseLong(cookies[1].getValue()))) {
            response.setStatus(200);
            categoriesRepository.deleteAll();
        }
        response.setStatus(403);
    }

    @PostMapping("/categories-get")
    @ResponseBody
    public Categories getCategories(@RequestBody Category category, HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length == 2
                && jwtAuthenticationFilter.validateToken(cookies[0].getValue(), Long.parseLong(cookies[1].getValue()))) {
            String url = category.getUrl();
            List<Category> result = new ArrayList<>();
            Document doc = Jsoup.connect(url).timeout(10000).validateTLSCertificates(false).get();
            doc.select("a[class='name js-category-item']")
                    .forEach(element -> {
                        Category c = new Category();
                        c.setName(element.text());
                        c.setUrl(element.attr("abs:href"));
                        result.add(c);
                    });
            Categories end = new Categories();
            end.setCategoryList(result);
            response.setStatus(200);
            return end;
        }
        response.setStatus(403);
        return null;
    }
}
