package com.spring.recipeapp.controller;

import com.spring.recipeapp.domain.Recipe;
import com.spring.recipeapp.service.RecipeService;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        // initialize all of the mocks
        MockitoAnnotations.initMocks(this);

        // initialize the index controller
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() throws Exception {
        // given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        recipes.add(recipe);

        when(recipeService.getRecipe()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);


        //when
        String viewName = indexController.getIndexPage(model);

        // check if the controller is returning the right string or page
        assertEquals("index", viewName);

        // checking the number of times methods are called
        verify(recipeService, times(1)).getRecipe();
        // argumentCaptor.capture() checks what argument or set is passed in
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        // finding the value of the argument
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }

}







