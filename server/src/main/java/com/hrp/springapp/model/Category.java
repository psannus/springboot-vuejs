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
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String url;

    private String name;
}
