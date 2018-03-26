package com.spring.recipeapp.repositories;

import com.spring.recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, Long> {


}
