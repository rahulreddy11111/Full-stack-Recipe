import React from "react"; // Imports React to use JSX and component functionality

// Functional component to display a single recipe card
function RecipeCard({ recipe }) { // Receives 'recipe' object as a prop
    return (
        // JSX for the card UI — a styled container for each recipe
        <div style={{ border: "1px solid #ccc", padding: "10px", marginBottom: "10px" }}>
            <h4>{recipe.name}</h4> {/* Displays the recipe name */}
            <p><strong>Cuisine:</strong> {recipe.cuisine}</p> {/* Shows recipe cuisine */}
            <p><strong>Prep Time:</strong> {recipe.cookTimeMinutes} min</p> {/* Shows cook time */}
        </div>
    );
}

export default RecipeCard; // Makes the component available for use in other files
