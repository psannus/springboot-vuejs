package com.hrp.springapp.controller;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoriesController {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @GetMapping("/categories")
    @ResponseBody
    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    @GetMapping("/categories-delete-all")
    @ResponseBody
    public void deleteAllCategories() {
        categoriesRepository.deleteAll();
    }

    @PostMapping("/categories-get")
    @ResponseBody
    public Categories getCategories(@RequestBody Category category) throws IOException {
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
        return end;
    }
}
