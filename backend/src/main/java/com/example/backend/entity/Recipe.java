package com.example.backend.entity; // Declares the entity package

import jakarta.persistence.*; // Imports JPA annotations for mapping to DB

@Entity // Marks this class as a JPA entity to be stored in the database
@Table(name = "recipes") // Maps this entity to the "recipes" table in the database
public class Recipe {

    @Id // Specifies the primary key field
    // @GeneratedValue(strategy = GenerationType.AUTO) // (Optional) Auto-generates the ID
    private Long id; // Unique identifier for each recipe

    private String name; // Recipe name field (e.g., "Pizza")

    private String cuisine; // Cuisine type field (e.g., "Italian")

    private Integer cookTimeMinutes; // Cooking time in minutes

    private String tags; // Comma-separated tags like "cheese,fastfood"

    public Recipe() {} // No-args constructor required by JPA

    public Long getId() { // Getter for ID
        return id;
    }

    public void setId(Long id) { // Setter for ID
        this.id = id;
    }

    public String getName() { // Getter for name
        return name;
    }

    public void setName(String name) { // Setter for name
        this.name = name;
    }

    public String getCuisine() { // Getter for cuisine
        return cuisine;
    }

    public void setCuisine(String cuisine) { // Setter for cuisine
        this.cuisine = cuisine;
    }

    public Integer getCookTimeMinutes() { // Getter for cook time
        return cookTimeMinutes;
    }

    public void setCookTimeMinutes(Integer cookTimeMinutes) { // Setter for cook time
        this.cookTimeMinutes = cookTimeMinutes;
    }

    public String getTags() { // Getter for tags
        return tags;
    }

    public void setTags(String tags) { // Setter for tags
        this.tags = tags;
    }
}
