package com.hrp.springapp;

import com.hrp.springapp.model.Category;
import com.hrp.springapp.model.Product;
import com.hrp.springapp.model.Products;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
        final Long[] id = {0L};
        doc.select("a[class='js-link-item']").eachAttr("abs:href")
                .parallelStream()
                .forEach(element -> {
                    id[0]++;
                    Product elementProduct = new Product();
                    Element elementDoc;
                    try {
                        elementDoc = Jsoup.connect(element)
                                .timeout(10000)
                                .validateTLSCertificates(false)
                                .get()
                                .selectFirst("div[itemtype='http://schema.org/Product']");

                        elementProduct.setImage(elementDoc.selectFirst("img[id='product-image-zoom']").absUrl("src"));
                        Document parsedDoc = Jsoup.parse(elementDoc.selectFirst("div[class='product-info-table']").html());

                        elementProduct.setName(parsedDoc.selectFirst("h1[id='product-name']").text());
                        elementProduct.setAmount(parsedDoc.selectFirst("div[class='js-quantity']").text());
                        elementProduct.setEan(parsedDoc.selectFirst("span[itemprop='sku']").text());
                        elementProduct.setPrice(parsedDoc.selectFirst("span[class='whole-number ']").text() + "." + parsedDoc.selectFirst("span[class='decimal']").text());
                        elementProduct.setId(id[0]);
                        elementProduct.setShelf(parsedDoc.selectFirst("div[class='aisle']").text().split("Riiul: ")[1].split(" EAN")[0]);
                        products.getProductList().add(elementProduct);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

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