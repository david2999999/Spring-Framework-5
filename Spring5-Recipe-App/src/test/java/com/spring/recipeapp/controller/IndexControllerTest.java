package com.spring.recipeapp.controller;

import com.spring.recipeapp.domain.Recipe;
import com.spring.recipeapp.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

    // Testing the view without opening a browser or tomcat
    @Test
    public void testMockMVC() throws Exception{
        // build the indexController
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        // perform a get request on "", checks if the status is ok
        // then check if the view name is "index"
        mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
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







