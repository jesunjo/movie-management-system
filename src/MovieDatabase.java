// Main movie database for the entire program.
// Loads movies from movies.json and stores them in a hashmap by lowercase title.
// Other classes should use methods like search() and getAllMovies()
// Instead of accessing the hashmap directly
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

public class MovieDatabase {
    private final HashMap<String, Movie> movies;

    public MovieDatabase() {
        movies = new HashMap<>();
        loadMovies();
    }
    // Reads data/movies.json using Gson and loads each movie into the HashMap.
    private void loadMovies() {
        Gson gson = new Gson();
        // Load from json
        try (FileReader reader = new FileReader("data/movies.json")) {
            Movie[] movieArray = gson.fromJson(reader, Movie[].class);

            if (movieArray == null) {
                return;
            }

            for (Movie movie : movieArray) {
                if (movie != null && movie.getTitle() != null) {
                    movies.put(movie.getTitle().trim().toLowerCase(), movie);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load movies from file"); // I pray this never prints
        }
    }
    // Searches for a movie by title. First checks for an exact hashmap match.
    // If no exact match exists, it searches for partial title matches.
    public ArrayList<Movie> search(String searchTerm) {
        ArrayList<Movie> results = new ArrayList<>();

        if (searchTerm == null || searchTerm.isBlank()) {
            return results;
        }

        String fixedSearch = searchTerm.trim().toLowerCase();
        Movie exactMatch = movies.get(fixedSearch);

        if (exactMatch != null) {
            results.add(exactMatch);
            return results;
        }

        for (Movie movie : movies.values()) {
            if (movie.getTitle().toLowerCase().contains(fixedSearch)) {
                results.add(movie);
            }
        }
        return results;
    }
    // Returns all movies as an ArrayList for browsing/display purposes.
    // Hashmap order is not guaranteed, so dont rely on this list being sorted.
    public ArrayList<Movie> getAllMovies() {
        return new ArrayList<>(movies.values());
    }
}