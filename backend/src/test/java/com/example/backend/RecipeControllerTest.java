package com.example.backend; // Package declaration

import com.example.backend.controller.RecipeController; // Importing controller
import com.example.backend.entity.Recipe;               // Importing entity
import com.example.backend.service.RecipeService;       // Importing service layer

import org.junit.jupiter.api.Test;                      // JUnit test annotation
import org.mockito.Mockito;                             // For mocking behavior

import org.springframework.beans.factory.annotation.Autowired; // For dependency injection
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; // Web layer test config
import org.springframework.boot.test.mock.mockito.MockBean; // Creates mock bean
import org.springframework.http.MediaType;             // For setting response type
import org.springframework.test.web.servlet.MockMvc;   // Performs HTTP requests

import java.util.List; // For List return type

import static org.mockito.ArgumentMatchers.anyLong; // Match any long argument
import static org.mockito.ArgumentMatchers.anyString; // Match any string argument
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; // For GET requests
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // For POST requests
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*; // For result assertions

@WebMvcTest(RecipeController.class) // Loads only RecipeController for testing
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc; // Injects mock HTTP client

    @MockBean
    private RecipeService recipeService; // Mocked RecipeService bean

    @Test
    void testLoadRecipes() throws Exception {
        mockMvc.perform(post("/api/recipes/load")) // Simulates POST request
                .andExpect(status().isOk());       // Checks status code
    }

    @Test
    void testSearchRecipes() throws Exception {
        Recipe recipe = new Recipe();              // Creates dummy Recipe object
        recipe.setId(1L);                          // Sets recipe ID
        recipe.setName("Burger");                 // Sets recipe name
        recipe.setCuisine("American");            // Sets recipe cuisine

        Mockito.when(recipeService.searchRecipes(anyString())) // Defines mock return
                .thenReturn(List.of(recipe));

        mockMvc.perform(get("/api/recipes/search").param("q", "Bur")) // Simulates GET with param
                .andExpect(status().isOk())               // Checks status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Checks response type
                .andExpect(jsonPath("$[0].name").value("Burger")); // Validates JSON content
    }

    @Test
    void testGetRecipeByIdFound() throws Exception {
        Recipe recipe = new Recipe();             // Creates recipe object
        recipe.setId(1L);                         // Sets ID
        recipe.setName("Pasta");                 // Sets name
        recipe.setCuisine("Italian");            // Sets cuisine

        Mockito.when(recipeService.getRecipeById(anyLong())) // Mock return for ID
                .thenReturn(recipe);

        mockMvc.perform(get("/api/recipes/1"))    // Simulates GET by ID
                .andExpect(status().isOk())       // Checks status
                .andExpect(jsonPath("$.name").value("Pasta")); // Validates JSON name
    }

    @Test
    void testGetRecipeByIdNotFound() throws Exception {
        Mockito.when(recipeService.getRecipeById(anyLong())) // Mocks not found case
                .thenReturn(null);

        mockMvc.perform(get("/api/recipes/999"))  // Simulates GET by invalid ID
                .andExpect(status().isNotFound()); // Checks for 404
    }
}
