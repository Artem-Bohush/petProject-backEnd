package com.gmail.bohush.art.petProjectBackEnd.repository;

import com.gmail.bohush.art.petProjectBackEnd.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT u FROM Category u where u.id = :id")
    Category customFindById(@Param("id") Long id);
}
