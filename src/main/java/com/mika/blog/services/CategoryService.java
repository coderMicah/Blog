package com.mika.blog.services;

import java.util.List;

import com.mika.blog.domain.entities.Category;

public interface CategoryService {
    List<Category> listCategories();

    Category createCategory(Category category);
}
