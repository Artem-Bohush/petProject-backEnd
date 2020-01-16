package com.gmail.bohush.art.petProjectBackEnd.service;

import com.gmail.bohush.art.petProjectBackEnd.entity.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByEmail(String email);

    User findById(Long id);

    User getByToken(String token);

    void save(User user);

    void delete(Long id);
}
