package com.spring.recipeapp.repositories;

import com.spring.recipeapp.domain.Category;
import com.spring.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// CRUD OPERATIONS FOR THE DATABASE
public interface RecipeRepository extends CrudRepository<Recipe, Long>{

    Optional<Category> findByDescription(String description);
}
