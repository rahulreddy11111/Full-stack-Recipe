import React from "react"; // Import React to use JSX and functional components

// Functional component that takes props for query state and search handler
function SearchBar({ query, setQuery, onSearch }) {

    // Handles Enter key press to trigger search when query length is valid
    const handleKeyDown = (e) => {
        if (e.key === "Enter" && query.length >= 3) {
            onSearch(); // Calls the search function passed from parent
        }
    };

    return (
        <div style={{ marginBottom: "20px" }}> {/* Container with spacing */}
            <input
                type="text" // Input field for user query
                value={query} // Controlled input value linked to state
                onChange={(e) => setQuery(e.target.value)} // Updates query on each keystroke
                onKeyDown={handleKeyDown} // Checks for Enter key to auto-search
                placeholder="Search by name or cuisine" // Input hint
                style={{ padding: "10px", width: "300px" }} // Basic styling
            />
            <button
                onClick={onSearch} // Triggers search when button clicked
                style={{ padding: "10px", marginLeft: "10px" }} // Styling for button
            >
                Search
            </button>
        </div>
    );
}

export default SearchBar; // Exports the component to be used in other files
