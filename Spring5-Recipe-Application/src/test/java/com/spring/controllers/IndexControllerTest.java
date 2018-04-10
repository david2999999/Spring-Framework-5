package com.spring.controllers;

import com.spring.domain.Recipe;
import com.spring.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

// Test for the Index Controller
// Each test will be used for test driven development
public class IndexControllerTest {

    // create mock fields
    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController controller;

    @Before
    public void setUp() throws Exception {
        // this will initialize all of the @Mock fields
        MockitoAnnotations.initMocks(this);

        // create a new index controller with the recipe service
        controller = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception {

        //Build a MockMvc instance by registering one or more
        // @Controller instances and configuring Spring MVC infrastructure programmatically.
        // Using this method, we do not need to run the controller inside a server
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        // perform a get method on the url "/"
        // if the status is ok
        // we will expect a name view name of index
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() throws Exception {

        //given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);

        // Use it to capture argument values for further assertions.
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when (get the view name from the controller method passing in a mock model)
        String viewName = controller.getIndexPage(model);


        //then
        // checks if the view name is equal to index
        assertEquals("index", viewName);

        // verifies if recipeService only runs one time when getting the index page
        verify(recipeService, times(1)).getRecipes();

        // checks if the model's addAttribute method run once and get the recipe that was passed into the method
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        // retrieve the value of the set
        Set<Recipe> setInController = argumentCaptor.getValue();

        // check if the set has 2 recipes
        assertEquals(2, setInController.size());
    }

}