package com.gmail.bohush.art.petProjectBackEnd.dto;

import com.gmail.bohush.art.petProjectBackEnd.entity.Category;
import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String lim;

    public static CategoryDto toCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setLim(category.getLim());
        return categoryDto;
    }
}
