package com.spring.recipeapp.repositories;

import com.spring.recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// CRUD OPERATIONS FOR THE DATABASE
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);

}
