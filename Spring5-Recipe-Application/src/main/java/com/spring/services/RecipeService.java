package com.spring.services;

import com.spring.commands.RecipeCommand;
import com.spring.domain.Recipe;

import java.util.Set;

// recipe service interface for all of the utility methods
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);


}
