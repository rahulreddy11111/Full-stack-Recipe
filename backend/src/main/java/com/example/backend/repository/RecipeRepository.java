package com.example.backend.repository; // Declares the package for the repository layer

import com.example.backend.entity.Recipe; // Imports the Recipe entity
import org.springframework.data.jpa.repository.JpaRepository; // Provides CRUD and query support
import org.springframework.stereotype.Repository; // Marks this interface as a Spring-managed Repository

import java.util.List; // Imports List for returning multiple records

@Repository // Indicates this is a Spring Data JPA repository (used in @ComponentScan)
public interface RecipeRepository extends JpaRepository<Recipe, Long> { // Extends JpaRepository to inherit CRUD methods

    // Custom finder method to search by name or cuisine with case-insensitive partial match
    List<Recipe> findByNameContainingIgnoreCaseOrCuisineContainingIgnoreCase(String name, String cuisine);
}
