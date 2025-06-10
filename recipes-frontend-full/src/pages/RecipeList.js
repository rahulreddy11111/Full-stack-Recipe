import React, { useState } from "react"; // Import React and state hook
import { searchRecipes } from "../api/recipeApi"; // Import API call function
import SearchBar from "../components/SearchBar"; // Import reusable search input
import RecipeCard from "../components/RecipeCard"; // Import UI card for each recipe

function RecipeList() {
    const [query, setQuery] = useState(""); // Holds user input from search bar
    const [recipes, setRecipes] = useState([]); // Stores fetched recipe list

    // Called when user triggers a search
    const handleSearch = async () => {
        if (query.length < 3) return; // Skip search if input too short
        const data = await searchRecipes(query); // Call backend API
        setRecipes(data); // Update state with fetched recipes
    };

    return (
        <div style={{ padding: "2rem" }}> {/* Main container with spacing */}
            <h2>Recipe Finder</h2> {/* Page heading */}
            <SearchBar
                query={query} // Pass current input value
                setQuery={setQuery} // Pass method to update input
                onSearch={handleSearch} // Pass search trigger function
            />
            <div>
                {recipes.map((recipe) => ( // Render each recipe as a card
                    <RecipeCard key={recipe.id} recipe={recipe} /> // Unique key for each card
                ))}
            </div>
        </div>
    );
}

export default RecipeList; // Export the component to be used in app
