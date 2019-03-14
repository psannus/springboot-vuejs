package com.hrp.springapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private String image;

    private String ean;

    private String price;

    private String amount;

    private String shelf;

}