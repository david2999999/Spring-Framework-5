package com.spring.recipeapp.repositories;

import com.spring.recipeapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// CRUD OPERATIONS FOR THE DATABASE
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{

    Optional<UnitOfMeasure> findByDescription(String description);
}
