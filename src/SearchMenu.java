import java.util.ArrayList;
import java.util.Scanner;

public class SearchMenu {
    public static void open(Scanner input, MovieDatabase database, Watchlist watchlist, ViewedList viewedList) {
        boolean search = true;

        // Search menu loop
        while (search) {
            System.out.println("\n| Search Movies Menu |\n");
            System.out.println("1. Browse Movies");
            System.out.println("2. Add New Movie");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            String userInput = input.nextLine().trim();

            // Once again handling the user putting random inputs
            if (userInput.endsWith(".")) {
                userInput = userInput.substring(0, userInput.length() - 1);
            }
            int choice;

            try {
                choice = Integer.parseInt(userInput);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a number from 1 to 3");
                continue;
            }

            switch (choice) {
                case 1:
                    browseMovies(input, database, watchlist, viewedList);
                    break;
                case 2:
                    addNewMovie(input, database);
                    break;
                case 3:
                    search = false;
                    break;
                default:
                    System.out.println("Invalid option. Please type any of the numbers listed above");
            }
        }
    }
    // The browseMovies method
    private static void browseMovies(Scanner input, MovieDatabase database, Watchlist watchlist, ViewedList viewedList) {
        ArrayList<Movie> movies = database.getAllMovies();
        int currentPage = 0;
        int moviesPerPage = 10;
        boolean browse = true;

        while (browse) {
            System.out.println("\n| Browse Movies |\n");
            // displays the movies with while numbered too. 
            int start = currentPage * moviesPerPage;
            int end = Math.min(start + moviesPerPage, movies.size());
            for (int i = start; i < end; i++) {
                System.out.println((i - start + 1) + ". " + movies.get(i));
            }

            System.out.println("\nS: Search for a Movie");
            System.out.println("N: Next Page");
            System.out.println("P: Previous Page");
            System.out.println("W #: Add Movie to Watchlist");
            System.out.println("V #: Add Movie to Viewed List");
            System.out.println("B: Back to Search Menu");
            System.out.print("Enter your choice: ");
            String userInput = input.nextLine().trim();


            // The ifelse chain of menu options
            if (userInput.equalsIgnoreCase("S")) {
                System.out.print("Enter name of movie: ");
                String searchTerm = input.nextLine().trim();
                
                ArrayList<Movie> results = database.search(searchTerm);

                if (results.isEmpty()) {
                    System.out.println("No movies found matching your search.");
                }
                else {
                    System.out.println("\nSearch Results:");
                    // This also numbers the results which i hope can be used to select a movie to be sent to the watch list.
                    for (int i = 0; i < results.size(); i++) {
                        System.out.println((i + 1) + ". " + results.get(i));
                    }
                }
            }
            else if (userInput.equalsIgnoreCase("N")) {
                if (end < movies.size()) {
                    currentPage++;
                }
                else {
                    System.out.println("No more pages to display.");
                }
            }
            else if (userInput.equalsIgnoreCase("P")) {
                if (currentPage > 0) {
                    currentPage--;
                }
                else {
                    System.out.println("You are on the first page");
                }
            }
            else if (userInput.toUpperCase().startsWith("W ")) {
                String numberText = userInput.substring(2).trim();

                try {
                    int movieNum = Integer.parseInt(numberText);
                    int moviesOnPage = end - start;

                    if (movieNum >= 1 && movieNum <= moviesOnPage) {
                        int actualIndex = start + movieNum - 1;
                        Movie selectedMovie = movies.get(actualIndex);
                        watchlist.addMovie(selectedMovie);
                        System.out.println(selectedMovie.getTitle() + " added to watchlist.");
                    }
                    else {
                        System.out.println("Invalid movie number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid movie number.");
                }
            }
            else if (userInput.toUpperCase().startsWith("V ")) {
                String numberText = userInput.substring(2).trim();

                try {
                    int movieNum = Integer.parseInt(numberText);
                    int moviesOnPage = end - start;

                    if (movieNum >= 1 && movieNum <= moviesOnPage) {
                        int actualIndex = start + movieNum - 1;
                        Movie selectedMovie = movies.get(actualIndex);
                        viewedList.addMovie(selectedMovie);
                        System.out.println(selectedMovie.getTitle() + " added to viewed list.");
                    }
                    else {
                        System.out.println("Invalid movie number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid movie number.");
                }
            }
            else if (userInput.equalsIgnoreCase("B")) {
                browse = false;
            }
            else {
                System.out.println("Invalid Option.");
            }
        }
    }
    // This is where movies get added permanently to the json file, so be careful
    private static void addNewMovie(Scanner input, MovieDatabase database) {
        System.out.println("\n| Add New Movie |\n");
        int year;

        System.out.print("Enter Movie Title: ");
        String title = input.nextLine().trim();

        System.out.print("Enter Genre: ");
        String genre = input.nextLine().trim();

        System.out.print("Enter Movie Year: ");
        String yearInput = input.nextLine().trim();

        try {
            year = Integer.parseInt(yearInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Year.");
            return;
        }
        Movie newMovie = new Movie(title, genre, year);
        boolean added = database.addMovie(newMovie);

        if (added) {
            System.out.println(title + " added to the movie database");
        }
        else {
            System.out.println("That movie already exists");
        }
    }
}