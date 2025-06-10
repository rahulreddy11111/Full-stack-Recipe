package com.example.backend; // Defines the package for organizing related classes

// Importing necessary classes
import com.example.backend.entity.Recipe; // Recipe entity (data model)
import com.example.backend.repository.RecipeRepository; // JPA Repository interface
import com.example.backend.service.RecipeService; // Business logic layer

import org.junit.jupiter.api.BeforeEach; // Used to run setup before each test
import org.junit.jupiter.api.Test; // Marks a method as a test case
import org.mockito.Mockito; // For creating mock objects
import org.springframework.boot.test.context.SpringBootTest; // Loads Spring Boot context for testing

import java.util.List; // Java collection to hold multiple recipes
import java.util.Optional; // Wrapper to handle possible null values

// Static imports for cleaner test syntax
import static org.junit.jupiter.api.Assertions.*; // Provides assert methods for test validations
import static org.mockito.Mockito.*; // Provides when(), verify(), etc. for mocking

@SpringBootTest // Informs Spring to load application context for integration testing
public class RecipeServiceTest {

    private RecipeRepository recipeRepository; // Mocked data access layer
    private RecipeService recipeService; // Service we want to test

    @BeforeEach // Runs before each test case
    void setUp() {
        recipeRepository = Mockito.mock(RecipeRepository.class); // Creates a mock version of the repository
        recipeService = new RecipeService(); // Initializes the service manually (no auto-wiring)
        recipeService.recipeRepository = recipeRepository; // Injects the mock repo into the service
    }

    @Test // Unit test for search functionality
    void testSearchRecipes() {
        Recipe recipe = new Recipe(); // Create dummy recipe for testing
        recipe.setId(1L); // Set unique ID
        recipe.setName("Pizza"); // Set name
        recipe.setCuisine("Italian"); // Set cuisine type
        recipe.setCookTimeMinutes(20); // Set cooking time
        recipe.setTags("fastfood,cheese"); // Set tags for filtering

        // Define behavior: when search is called, return this dummy list
        when(recipeRepository.findByNameContainingIgnoreCaseOrCuisineContainingIgnoreCase("Pizza", "Pizza"))
                .thenReturn(List.of(recipe));

        List<Recipe> result = recipeService.searchRecipes("Pizza"); // Call actual service method

        assertEquals(1, result.size()); // Verify list contains 1 item
        assertEquals("Pizza", result.get(0).getName()); // Verify item name matches expected
    }

    @Test // Unit test for getting recipe by ID when recipe exists
    void testGetRecipeByIdFound() {
        Recipe recipe = new Recipe(); // Create dummy recipe
        recipe.setId(2L); // Set ID
        recipe.setName("Sushi"); // Set name
        recipe.setCuisine("Japanese"); // Set cuisine

        // Mock: When findById is called with 2L, return recipe wrapped in Optional
        when(recipeRepository.findById(2L)).thenReturn(Optional.of(recipe));

        Recipe found = recipeService.getRecipeById(2L); // Call method to test

        assertNotNull(found); // Check result is not null
        assertEquals("Sushi", found.getName()); // Check name is "Sushi"
    }

    @Test // Unit test when recipe is not found
    void testGetRecipeByIdNotFound() {
        // Mock: When findById is called with 100L, return empty (recipe not present)
        when(recipeRepository.findById(100L)).thenReturn(Optional.empty());

        Recipe found = recipeService.getRecipeById(100L); // Call method to test

        assertNull(found); // Expected to be null since recipe not found
    }
}
