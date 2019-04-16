package com.hrp.springapp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerTest {

    @Autowired
    private ProductsController productsController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contexLoads() {
        assert productsController != null;
    }

    @Test
    public void getAllProducts() throws Exception {
        MvcResult loginResult = this.mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\",\"password\": \"password\"}"))
                .andExpect(status().isOk())
                .andReturn();
        this.mockMvc.perform(get("/products")
                .cookie(loginResult.getResponse()
                        .getCookies()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void saveProductListToUser() throws Exception {
        MvcResult loginResult = this.mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\",\"password\": \"password\"}"))
                .andExpect(status().isOk())
                .andReturn();
        this.mockMvc.perform(post("/basket-save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\",\"productList\":[{\"id\":null,\"name\":\"7 Days kakaokreemi täidisega sarvesai 60 g\",\"image\":\"https://s3-eu-west-1.amazonaws.com/balticsimages/images/180x220/9f05875047adb922705cc22d3f19b958.png\",\"ean\":\"5201360521210\",\"price\":\"0.45\",\"amount\":\"0.065 kg\",\"shelf\":\"Kuivained / Saiad, kuklid, kauasäilivad\"}]}")
                .cookie(loginResult.getResponse()
                        .getCookies()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getBasketById() throws Exception {
        MvcResult loginResult = this.mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\",\"password\": \"password\"}"))
                .andExpect(status().isOk())
                .andReturn();
        MvcResult response = this.mockMvc.perform(post("/basket-list")
                .cookie(loginResult.getResponse()
                        .getCookies()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assert !response.getResponse().getContentAsString().isEmpty();
    }
}
