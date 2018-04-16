package com.spring.controllers;

import com.spring.commands.RecipeCommand;
import com.spring.domain.Recipe;
import com.spring.exceptions.NotFoundException;
import com.spring.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {


    // create a mock recipeService for the controller
    @Mock
    RecipeService recipeService;

    RecipeController controller;

    // MockMvc to test the routing of the controller
    // without running a real server
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        // initialize all of the fields with the @Mock notation
        MockitoAnnotations.initMocks(this);

        // create a new recipe controller to test
        controller = new RecipeController(recipeService);

        // create a new mockMvc to test for routing
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetRecipe() throws Exception {

        // create a recipe that will be used to test the controller
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        // when the recipe service uses the findById method
        // return the recipe that was created above
        when(recipeService.findById(anyLong())).thenReturn(recipe);

        // performs a get method to the url
        // if the status is ok
        // check if the view name is recipe/show
        // also check if the model of the view contains recipe
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testGetRecipeNotFound() throws Exception {

        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    public void testGetRecipeNumberFormatException() throws Exception {

        mockMvc.perform(get("/recipe/asdf/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

    @Test
    public void testGetNewRecipeForm() throws Exception {
        // create a command method to be tested with the recipe form
        RecipeCommand command = new RecipeCommand();

        // perform a get method to the url with the form
        // if the status is ok
        // check if the view name is recipe/recipeform
        // also check if the model has an attribute called recipe
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testPostNewRecipeForm() throws Exception {
        // create a command object to be tested with the form
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        // when there is a saveRecipeCommand method, return the command that was created above
        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        // when a post method is sent to /recipe
        // MediaType is A subclass of MimeType that adds support for quality parameters as defined in the HTTP specification.
        // checks if the status has 300 something
        // check if the view name is to redirect the page
        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        // create a command object to be tested with the form
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        // perform get method to the url
        // if the url has status ok
        // check if the view name is from recipe/recipeform
        // also check if the model has an attribute of recipe
        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1)).deleteById(anyLong());
    }

}