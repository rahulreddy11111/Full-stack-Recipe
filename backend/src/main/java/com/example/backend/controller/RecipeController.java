package com.example.backend.controller; // Defines this class in the controller package

import com.example.backend.entity.Recipe; // Imports the Recipe entity class
import com.example.backend.service.RecipeService; // Imports the service used to handle business logic
import io.swagger.v3.oas.annotations.Operation; // Swagger annotation to describe the endpoint
import io.swagger.v3.oas.annotations.tags.Tag; // Swagger tag to group endpoints
import org.springframework.beans.factory.annotation.Autowired; // Used to auto-inject dependencies
import org.springframework.http.ResponseEntity; // Represents HTTP response with body and status
import org.springframework.web.bind.annotation.*; // Spring annotations for REST API

import java.util.List; // Importing List interface

@RestController // Marks this class as a REST controller (returns JSON, not HTML)
@RequestMapping("/api/recipes") // Base path for all endpoints in this controller
@Tag(name = "Recipe API", description = "Operations related to Recipes") // Swagger tag for grouping endpoints
public class RecipeController {

    @Autowired // Automatically injects RecipeService instance from Spring context
    private RecipeService recipeService;

    @PostMapping("/load") // Maps HTTP POST requests to /api/recipes/load
    @Operation(summary = "Load all recipes from external API into in-memory DB") // Swagger description
    public ResponseEntity<List<Recipe>> loadRecipes() {
        List<Recipe> loaded = recipeService.loadRecipesFromExternalApi(); // Calls service to fetch and load recipes
        return ResponseEntity.ok(loaded); // Returns HTTP 200 with list of recipes
    }

    @GetMapping("/search") // Maps GET requests to /api/recipes/search
    @Operation(summary = "Search recipes by name or cuisine (free text search)") // Swagger summary
    public ResponseEntity<List<Recipe>> searchRecipes(@RequestParam String q) {
        List<Recipe> recipes = recipeService.searchRecipes(q); // Calls service method to search recipes
        return ResponseEntity.ok(recipes); // Returns matching recipes with status 200
    }

    @GetMapping("/{id}") // Maps GET requests to /api/recipes/{id}
    @Operation(summary = "Get recipe by ID") // Swagger summary
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id); // Calls service to get recipe by ID
        if (recipe == null) {
            return ResponseEntity.notFound().build(); // Returns 404 if recipe not found
        }
        return ResponseEntity.ok(recipe); // Returns recipe if found
    }
}
