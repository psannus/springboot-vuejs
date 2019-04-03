package com.hrp.springapp.service;

import com.hrp.springapp.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findById(Long id);

    List<User> findAll();
}