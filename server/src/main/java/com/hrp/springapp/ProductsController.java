package com.hrp.springapp;

import com.hrp.springapp.model.Category;
import com.hrp.springapp.model.Product;
import com.hrp.springapp.model.Products;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/products-list")
    @ResponseBody
    public Products getListOfProducts(@RequestBody Category category) throws IOException {
        String url = category.getUrl();
        Products products = new Products();
        Document doc = Jsoup.connect(url).timeout(10000).validateTLSCertificates(false).get();
        Elements elements = doc.getElementsByClass("js-link-item");
        Long id = 0L;
        for (Element element : elements) {
            id++;
            Product elementProduct = new Product();
            String elementUrl = element.attr("abs:href");
            Document elementDoc = Jsoup.connect(elementUrl).timeout(10000).validateTLSCertificates(false).get();
            elementProduct.setName(elementDoc.getElementById("product-name").text());
            elementProduct.setAmount(elementDoc.getElementsByClass("js-quantity").text());
            elementProduct.setEan(elementDoc.select("span[itemprop='sku']").text());
            elementProduct.setImage(elementDoc.getElementById("product-image-zoom").absUrl("src"));
            Elements price = elementDoc.getElementsByClass("price");
            elementProduct.setPrice(price.first().text() + "." + price.next().text());
            elementProduct.setId(id);
            elementProduct.setShelf(elementDoc.getElementsByClass("aisle").first().text().split(": ")[1].split(" /")[0]);
            products.getProductList().add(elementProduct);
        }
        return products;
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