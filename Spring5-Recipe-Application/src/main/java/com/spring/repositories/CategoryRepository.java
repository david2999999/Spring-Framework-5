package com.spring.repositories;

import com.spring.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// the CrudRepository contains most of the CRUD methods
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
