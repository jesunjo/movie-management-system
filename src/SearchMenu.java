import java.util.ArrayList;
import java.util.Scanner;

public class SearchMenu {
    public static void open(Scanner input, MovieDatabase database) {
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
                    browseMovies(input, database);
                    break;
                case 2:
                    //addNewMovie(input);
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
    private static void browseMovies(Scanner input, MovieDatabase database) {
        ArrayList<Movie> movies = database.getAllMovies();
        boolean browse = true;

        while (browse) {
            System.out.println("\n| Browse Movies |\n");
            // displays the movies with while numbered too. 
            for (int i = 0; i < movies.size(); i++) {
                System.out.println((i + 1) + ". " + movies.get(i));
            }

            System.out.println("\nB: Back to Search Menu");
            System.out.println("S: Search for a Movie");
            System.out.print("Enter your choice: ");
            String userInput = input.nextLine().trim();
            // Exits the menu
            if (userInput.equalsIgnoreCase("B")) {
                browse = false;
            }
            else if (userInput.equalsIgnoreCase("S")) {
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
            else {
                System.out.println("Invalid Option.");
            }
        }
    }
}
