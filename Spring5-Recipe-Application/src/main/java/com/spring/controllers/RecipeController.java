package com.spring.controllers;

import com.spring.services.RecipeService;
import com.spring.commands.RecipeCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


// Controller that will be in charge of all of the CRUD routing methods on the recipe
@Controller
public class RecipeController {

    // create a recipeService that has all of the CRUD methods
    private final RecipeService recipeService;

    // the recipe service is auto wired using constructor auto wired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // show the individual recipe
    // the request mapping follows a RESTful design pattern
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){

        // find the recipe by its ID and sent to the HTML
        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        // the thymeleaf view page
        return "recipe/show";
    }

    // route to show the form for a new recipe
    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        // create a new recipe command object to be bind to the form
        model.addAttribute("recipe", new RecipeCommand());

        // the thymeleaf view page for the form
        return "recipe/recipeform";
    }

    // route to show the form to update a recipe
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        // find the command object that will be used to bind to the form
        // the form will be populated with the data of the command object
        // inside the recipeService method, the original domain object will be
        // converted to a command object
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        // the thymeleaf view page to update a recipe
        return "recipe/recipeform";
    }

    // Post route to save or update a recipe
    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        // save the recipe and return the command object that was saved
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        // redirect the user to show the page of the recipe that was saved or updated
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }
}
