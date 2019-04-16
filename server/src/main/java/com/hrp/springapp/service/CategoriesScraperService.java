package com.hrp.springapp.service;

import com.hrp.springapp.model.Categories;
import com.hrp.springapp.model.Category;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesScraperService {
    public Categories scrapeCategory(Category category) throws IOException {
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
