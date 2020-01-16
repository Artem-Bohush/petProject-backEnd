package com.gmail.bohush.art.petProjectBackEnd.rest;

import com.gmail.bohush.art.petProjectBackEnd.dto.CategoryDto;
import com.gmail.bohush.art.petProjectBackEnd.dto.RecordDto;
import com.gmail.bohush.art.petProjectBackEnd.dto.UserDto;
import com.gmail.bohush.art.petProjectBackEnd.entity.*;
import com.gmail.bohush.art.petProjectBackEnd.service.CategoryService;
import com.gmail.bohush.art.petProjectBackEnd.service.RecordService;
import com.gmail.bohush.art.petProjectBackEnd.service.RecordTypeService;
import com.gmail.bohush.art.petProjectBackEnd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/record")
@CrossOrigin(origins={ "http://localhost:3000"})
//@Slf4j
public class RecordController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final RecordService recordService;
    private final RecordTypeService recordTypeService;


    public RecordController(UserService userService, CategoryService categoryService, RecordService recordService,
                            RecordTypeService recordTypeService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.recordService = recordService;
        this.recordTypeService = recordTypeService;
    }

    @GetMapping("/getCategories")
    public List<CategoryDto> getAllCategories(@RequestHeader(value="authorization") String token) {
        User user = userService.getByToken(token);
        List<Category> categories = user.getCategories();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categories.forEach(category -> {
            categoryDtos.add(CategoryDto.toCategoryDto(category));
        });
        return categoryDtos;
    }

    @PostMapping("/addRecord")
    public HttpStatus addNewRecord(@RequestHeader(value="authorization") String token, @RequestBody RecordDto recordDto) {
        User user = userService.getByToken(token);
        Record record = RecordDto.toRecord(recordDto);
        record.setType(recordTypeService.findByName(recordDto.getType()));
        record.setCategory(categoryService.findById(recordDto.getCategoryId()));
        record.setUser(user);
        recordService.save(record);
        return HttpStatus.OK;
    }

    @PostMapping("/addCategory")
    public HttpStatus addNewCategory(@RequestHeader(value="authorization") String token, @RequestBody CategoryDto categoryDto) {
        User user = userService.getByToken(token);
        Category ct = new Category();
        ct.setName(categoryDto.getName());
        ct.setLim(categoryDto.getLim());
        ct.setUser(user);
        categoryService.save(ct);
        return HttpStatus.OK;
    }

    @PutMapping("/editCategory")
    public HttpStatus editCategory(@RequestHeader(value="authorization") String token, @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.findById(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setLim(categoryDto.getLim());
        categoryService.save(category);
        return HttpStatus.OK;
    }

    @DeleteMapping("/deleteCategory")
    public HttpStatus deleteCategory(@RequestHeader(value="authorization") String token, @RequestBody CategoryDto categoryDto) {
        categoryService.delete(categoryDto.getId());
        return HttpStatus.OK;
    }
}