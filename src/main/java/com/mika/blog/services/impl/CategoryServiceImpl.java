package com.mika.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mika.blog.domain.entities.Category;
import com.mika.blog.repositories.CategoryRepository;
import com.mika.blog.services.CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public List<Category> listCategories() {
        return repository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        String categoryName = category.getName();
        if (repository.existsByNameIgnoreCase(categoryName)) {
            throw new IllegalArgumentException("Category already exists with name: " + categoryName);
        }
        return repository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category = repository.findById(id);
        if (category.isPresent()) {
            if (!category.get().getPosts().isEmpty()) {
                throw new IllegalStateException("Category has posts associated with it");
            }
            repository.deleteById(category.get().getId());
        }

    }

}
