package com.hrp.springapp.service;

import com.hrp.springapp.model.Category;
import com.hrp.springapp.model.Product;
import com.hrp.springapp.model.Products;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ProductsScraperService {

    public Products scrapeListOfProducts(Category category, String cookieId) throws IOException {
        String url = category.getUrl();
        Products products = new Products();
        products.setUserId(Long.parseLong(cookieId));
        Document doc = Jsoup.connect(url).timeout(10000).validateTLSCertificates(false).get();
        doc.select("a[class='js-link-item']").eachAttr("abs:href")
                .parallelStream()
                .forEach(element -> {
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
                        String ean = parsedDoc.selectFirst("span[itemprop='sku']").text();
                        elementProduct.setEan(ean);
                        String[] price = new String[2];
                        parsedDoc.select("*").parallelStream().forEach(e -> {
                            if (e.tagName().equals("h1") && e.id().equals("product-name")) {
                                elementProduct.setName(e.text());
                            }
                            if (e.tagName().equals("div") && e.className().equals("js-quantity")) {
                                elementProduct.setAmount(e.text());
                            }
                            if (e.tagName().equals("span") && e.className().equals("whole-number")) {
                                price[0] = e.text();
                            }
                            if (e.tagName().equals("span") && e.className().equals("decimal")) {
                                price[1] = e.text();
                            }
                            if (e.tagName().equals("div") && e.className().equals("aisle")) {
                                elementProduct.setShelf(e.text().split("Riiul: ")[1].split(" EAN")[0]);
                            }
                        });
                        elementProduct.setPrice(price[0] + "." + price[1]);
                        products.getProductList().add(elementProduct);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        return products;
    }

    public ResponseEntity getMockProducts() throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(
                new String(Files.readAllBytes(Paths.get("~/../db.json")), StandardCharsets.UTF_8),
                httpHeaders,
                HttpStatus.OK
        );
    }
}
