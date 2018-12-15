package com.char1.api.repository;

import com.char1.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findById(int id);
    boolean existsByName(String name);
}
