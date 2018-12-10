package com.char1.api.controller;

import com.char1.api.entity.Category;
import com.char1.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(value = "/category/{id}")
    public Category getCategory(@PathVariable String id) {
        return categoryRepository.findById(Integer.parseInt(id));
    }

    @GetMapping(value = "/category")
    public List<Category> getAllcategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/category")
    public Category newBankAccount(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
}
