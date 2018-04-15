package com.spring.services;

import com.spring.commands.RecipeCommand;
import com.spring.converters.RecipeCommandToRecipe;
import com.spring.converters.RecipeToRecipeCommand;
import com.spring.domain.Recipe;
import com.spring.exceptions.NotFoundException;
import com.spring.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    // RecipeRepository field for CRUD methods
    private final RecipeRepository recipeRepository;

    // Converter fields to convert domain object to command object
    // and also command object to domain object
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    // Constructor auto wire for all of the fields
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    // method to retrieve all of the recipes from the database
    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service");

        // create a set of recipe to be returned
        Set<Recipe> recipeSet = new HashSet<>();

        // find all of the recipes and add to the set
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);

        return recipeSet;
    }

    // method to find a recipe by ID
    @Override
    public Recipe findById(Long l) {
        // Optional is A container object which may or may not contain a non-null value.
        // If a value is present, isPresent() will return true and get() will return the value.
        // retrieve the object by its ID from the repository
        Optional<Recipe> recipeOptional = recipeRepository.findById(l);

        // if the recipe is not found
        if (!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe Not Found. For recipe id: " + l);
        }

        // return the actual value from the optional class
        return recipeOptional.get();
    }

    // find a command object by its ID
    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {
        // find the Recipe domain object at first
        // the convert the domain object to the command object
        // then return the command object
        return recipeToRecipeCommand.convert(findById(l));
    }


    // method to save a command object recipe
    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        // convert the command object to a regular recipe object
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        // save the recipe and return the recipe
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());

        // return the recipe domain object after it is converted
        // to a command object
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long idToDelete) {
        recipeRepository.deleteById(idToDelete);
    }
}
