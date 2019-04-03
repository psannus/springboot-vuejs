package com.hrp.springapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    private String jwt;
}
