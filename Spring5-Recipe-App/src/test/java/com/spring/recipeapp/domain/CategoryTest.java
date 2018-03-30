package com.spring.recipeapp.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

// This is a unit test
public class CategoryTest {

    Category category;

    // this will run before each test
    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() throws Exception {
        Long idValue = 4L;

        category.setId(idValue);

        assertEquals(idValue, (Long)category.getId());
    }

    @Test
    public void getDescription() throws Exception {
        String description = "Test is a test";

        category.setDescription(description);

        assertEquals(description, category.getDescription());

    }

    @Test
    public void getRecipes() throws Exception {
        Recipe testRecipe = new Recipe();
        Set<Recipe> testRecipeSet = new HashSet<>();
        testRecipeSet.add(testRecipe);

        category.setRecipes(testRecipeSet);

        assertEquals(testRecipeSet, category.getRecipes());

    }

}