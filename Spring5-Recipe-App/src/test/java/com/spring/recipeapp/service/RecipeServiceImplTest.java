package com.spring.recipeapp.service;

import com.spring.recipeapp.domain.Recipe;
import com.spring.recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipe() throws Exception {

        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        // return the hashset recipesData when recipeService.getRecipe is called
        when(recipeService.getRecipe()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipe();

        assertEquals(recipes.size(), 1);

        // verifying if the findAll method of recipeRepository is called once
        verify(recipeRepository, times(1)).findAll();
    }

}