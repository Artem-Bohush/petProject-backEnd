package com.gmail.bohush.art.petProjectBackEnd.service.impl;

import com.gmail.bohush.art.petProjectBackEnd.entity.Category;
import com.gmail.bohush.art.petProjectBackEnd.repository.CategoryRepository;
import com.gmail.bohush.art.petProjectBackEnd.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.customFindById(id);
        categoryRepository.delete(category);
    }

    @Override
    public Category findById(Long id) {
        Category category = categoryRepository.customFindById(id);
        return category;
    }
}
