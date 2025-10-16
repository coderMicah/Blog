package com.mika.blog.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mika.blog.domain.dtos.CategoryDto;
import com.mika.blog.domain.dtos.CreateCategoryRequest;
import com.mika.blog.domain.entities.Category;
import com.mika.blog.mappers.CategoryMapper;
import com.mika.blog.services.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categories = categoryService.listCategories()
                .stream()
                .map(category -> categoryMapper.toDto(category))
                .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Category categoryToCreate = categoryMapper.toEntity(createCategoryRequest);
        Category savedCategory = categoryService.createCategory(categoryToCreate);

        // URI location = ServletUriComponentsBuilder
        // .fromCurrentRequest() // /api/v1/categories/create
        // .path("/{id}") // append /{id}
        // .buildAndExpand(savedCategory.getId())
        // .toUri();
        return new ResponseEntity<CategoryDto>(categoryMapper.toDto(savedCategory), HttpStatus.CREATED);
    }
}
