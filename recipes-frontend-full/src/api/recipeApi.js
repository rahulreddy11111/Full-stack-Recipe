import axios from 'axios'; // Imports axios library to make HTTP requests

const BASE_URL = "http://localhost:8080/api/recipes"; // Base URL for the backend API

// Function to search recipes using a query string
export const searchRecipes = async (query) => {
    // Makes a GET request to backend with query parameter `q`
    const response = await axios.get(`${BASE_URL}/search?q=${query}`);

    // Returns the response body (recipe list) from backend
    return response.data;
};
