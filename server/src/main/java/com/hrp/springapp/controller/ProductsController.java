package com.hrp.springapp.controller;

import com.hrp.springapp.jwt.JwtAuthenticationFilter;
import com.hrp.springapp.model.Category;
import com.hrp.springapp.model.Product;
import com.hrp.springapp.model.Products;
import com.hrp.springapp.repository.ProductsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @PostMapping("/basket-save")
    @ResponseBody
    public void saveProducts(@RequestBody Products products, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length == 2
                && jwtAuthenticationFilter.validateToken(cookies[0].getValue(), Long.parseLong(cookies[1].getValue()))) {
            System.out.println(products);
            productsRepository.save(products);
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
            Optional<Products> found = Optional.empty();
            for (Products p : productsRepository.findAll()) {
                if (p.getProductList().contains(product)) {
                    found = Optional.of(p);
                    break;
                }
            }
            if (found.isPresent()) {
                Products tempP = found.get();
                productsRepository.delete(tempP);
                tempP.getProductList().remove(product);
                productsRepository.save(tempP);
                response.setStatus(200);
            }
            response.setStatus(400);
        }
        response.setStatus(403);
    }

    @RequestMapping("/basket-list")
    @ResponseBody
    public List<Products> getBasketList(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length == 2
                && jwtAuthenticationFilter.validateToken(cookies[0].getValue(), Long.parseLong(cookies[1].getValue()))) {
            Long id = Long.parseLong(cookies[1].getValue());
            response.setStatus(200);
            return productsRepository.findAll().stream().filter(p ->
                    p.getUserId().equals(id)).collect(Collectors.toList());
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
            return productsRepository.findAll();
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
            productsRepository.deleteAll();
            response.setStatus(200);
        }
        response.setStatus(403);
    }

    @PostMapping("/products-list")
    @ResponseBody
    public Products getListOfProducts(@RequestBody Category category, HttpServletRequest request,
                                      HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length == 2
                && jwtAuthenticationFilter.validateToken(cookies[0].getValue(), Long.parseLong(cookies[1].getValue()))) {
            String url = category.getUrl();
            Products products = new Products();
            products.setUserId(Long.parseLong(cookies[1].getValue()));
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
            response.setStatus(200);
            return products;
        }
        response.setStatus(403);
        return null;
    }

    @GetMapping("/products-mock")
    public ResponseEntity getAllProductsMock(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length == 2
                && jwtAuthenticationFilter.validateToken(cookies[0].getValue(), Long.parseLong(cookies[1].getValue()))) {
            return new ResponseEntity<>(
                    new String(Files.readAllBytes(Paths.get("~/../db.json")), StandardCharsets.UTF_8),
                    httpHeaders,
                    HttpStatus.OK
            );
        }
        response.setStatus(403);
        return null;
    }

}