package com.char1.api.controller;

import com.char1.api.controller.exception.DuplicateEntityException;
import com.char1.api.controller.exception.EntityNotFoundException;
import com.char1.api.entity.Category;
import com.char1.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(value = "/{id}")
    public Category getCategory(@PathVariable int id) {
        if (!categoryRepository.existsById(id)) throw new EntityNotFoundException("category");
        return categoryRepository.findById(id);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        if (categoryRepository.existsByName(category.getName())) throw new DuplicateEntityException();
        return categoryRepository.save(category);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCategory(@PathVariable int id) {
        if (!categoryRepository.existsById(id)) throw new EntityNotFoundException("category");
        categoryRepository.deleteById(id);
        return;
    }
}
