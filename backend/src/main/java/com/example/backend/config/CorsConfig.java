package com.example.backend.config; // Package for configuration-related classes

import org.springframework.context.annotation.Bean; // Marks method as Spring-managed bean
import org.springframework.context.annotation.Configuration; // Marks this class as a config class for Spring context
import org.springframework.web.servlet.config.annotation.*; // Imports Web MVC config interfaces

@Configuration // Tells Spring this class provides configuration (like XML used to do earlier)
public class CorsConfig { // Defines a configuration class for setting up CORS

    @Bean // Registers this method’s return value as a Spring bean in the application context
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() { // Returns a custom WebMvcConfigurer implementation
            @Override
            public void addCorsMappings(CorsRegistry registry) { // Method to configure CORS mappings
                registry.addMapping("/**") // Apply CORS settings to all API paths
                        .allowedOrigins("http://localhost:3000") // Allow requests only from this origin (React frontend in dev)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow only these HTTP methods
                        .allowedHeaders("*") // Accept all headers in incoming requests
                        .exposedHeaders("Authorization") // Optional: allow client to access this specific response header
                        .allowCredentials(true); // Allow sending cookies and credentials with the request
            }
        };
    }
}
