package com.example.backend.service; // Declares the package for the service layer

import com.example.backend.entity.Recipe; // Imports the Recipe entity
import com.example.backend.repository.RecipeRepository; // Imports the Recipe repository interface
import org.springframework.beans.factory.annotation.Autowired; // Used for dependency injection
import org.springframework.http.ResponseEntity; // Represents HTTP response from external API
import org.springframework.stereotype.Service; // Marks this class as a service component
import org.springframework.web.client.RestTemplate; // For making REST API calls

import java.util.List; // For handling list of recipes

@Service // Declares this class as a Spring-managed service component
public class RecipeService {

    private static final String EXTERNAL_API_URL = "https://dummyjson.com/recipes"; // URL to fetch external recipes

    @Autowired
    public RecipeRepository recipeRepository; // Injects the repository for DB access

    private final RestTemplate restTemplate = new RestTemplate(); // Used to call external REST APIs

    // Loads recipe data from the external API and saves to DB
    public List<Recipe> loadRecipesFromExternalApi() {
        ResponseEntity<RecipeApiResponse> response = restTemplate.getForEntity(EXTERNAL_API_URL, RecipeApiResponse.class); // Calls external API
        RecipeApiResponse apiResponse = response.getBody(); // Extracts response body

        if (apiResponse != null && apiResponse.recipes != null) { // Checks if data is valid
            recipeRepository.saveAll(apiResponse.recipes); // Saves recipes to the database
            return apiResponse.recipes; // Returns saved recipes
        }
        return List.of(); // Returns empty list if no data
    }

    // Performs free text search on name or cuisine
    public List<Recipe> searchRecipes(String query) {
        return recipeRepository.findByNameContainingIgnoreCaseOrCuisineContainingIgnoreCase(query, query); // Calls custom query method
    }

    // Retrieves a recipe by its ID from the database
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null); // Returns recipe or null if not found
    }

    // Inner static class to match external API response structure
    public static class RecipeApiResponse {
        public List<Recipe> recipes; // Field to hold recipe list from API response
    }
}
