package com.spring.recipeapp.service;

import com.spring.recipeapp.commands.RecipeCommand;
import com.spring.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

}
