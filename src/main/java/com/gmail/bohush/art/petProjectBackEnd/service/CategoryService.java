package com.gmail.bohush.art.petProjectBackEnd.service;

import com.gmail.bohush.art.petProjectBackEnd.entity.Category;

public interface CategoryService {

    void save(Category category);

    void delete(Long id);

    Category findById(Long id);
}
