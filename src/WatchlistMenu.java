import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WatchlistMenu {
    public static void open(Scanner input, Watchlist watchlist) {
        boolean menu = true;
        boolean ascending = true;
        int currentPage = 0;
        int moviesPerPage = 10;

        while (menu) {
            // Movies and priorities from linked list
            ArrayList<Movie> movies = watchlist.toArrayList();
            ArrayList<Integer> priorities = watchlist.getPriorities();

            if (!ascending) {
                Collections.reverse(movies);
                Collections.reverse(priorities);
            }

            System.out.println("\n| Watchlist Menu |\n");
            
            if (movies.isEmpty()) {
                System.out.println("Your watchlist is empty.");
            }
            else {
                int start = currentPage * moviesPerPage;
                int end = Math.min(start + moviesPerPage, movies.size());

                for (int i = start; i < end; i++) {
                    System.out.println((i-start + 1) + ". [Priority: " + priorities.get(i) + "]" + movies.get(i));
                }
            }
            System.out.println("\nN: Next Page");
            System.out.println("P: Previous Page");
            System.out.println("R #: Remove Movie");
            System.out.println("C #: Modify Priority");
            System.out.println("S: Sort Priority Ascending/Descending");
            System.out.println("B: Back to Main Menu");
            System.out.print("Enter your choice: ");

            String userInput = input.nextLine().trim();

            // ifelse chain for menus
            
            // Next
            if (userInput.equalsIgnoreCase("N")) {
                if ((currentPage + 1) * moviesPerPage < movies.size()) {
                    currentPage++;
                } else {
                    System.out.println("No more pages to display");
                }
            }
            // Previous
            else if (userInput.equalsIgnoreCase("P")) {
                if (currentPage > 0) {
                    currentPage--;
                } else {
                    System.out.println("You are on the first page");
                }
            }
            // Remove
            else if (userInput.toUpperCase().startsWith("R ")) {
                String numText = userInput.substring(2).trim();

                try {
                    int movieNum = Integer.parseInt(numText);
                    int start = currentPage * moviesPerPage;
                    int end = Math.min(start + moviesPerPage, movies.size());
                    int moviesOnPage = end - start;
                    
                    if (movieNum >= 1 && movieNum <= moviesOnPage) {
                        int actualIndex = start + movieNum - 1;
                        Movie selectedMovie = movies.get(actualIndex);
                        boolean removed = watchlist.removeMovie(selectedMovie.getTitle());
                        
                        if (removed) {
                            System.out.println(selectedMovie.getTitle() + " removed from watchlist.");
                        } else {
                            System.out.println("Failed to remove " + selectedMovie.getTitle());
                        }
                        // Refresh page basically if its empty
                        if (currentPage > 0 && currentPage * moviesPerPage >= watchlist.getSize()) {
                            currentPage--;
                        }
                    else {
                        System.out.println("Invalid Movie Number");
                    }
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input for movie number");
                }
            }
            // Change
            else if (userInput.toUpperCase().startsWith("C ")) {
                String numText = userInput.substring(2).trim();

                try {
                    int movieNum = Integer.parseInt(numText);
                    int start = currentPage * moviesPerPage;
                    int end = Math.min(start + moviesPerPage, movies.size());
                    int moviesOnPage = end - start;

                    if (movieNum >= 1 && movieNum <= moviesOnPage) {
                        int actualIndex = start + movieNum - 1;
                        Movie selectedMovie = movies.get(actualIndex);
                        System.out.print("Enter New Priority: ");
                        String priorityInput = input.nextLine().trim();

                        try {
                            int newPriority = Integer.parseInt(priorityInput);
                            boolean changed  = watchlist.changePriority(selectedMovie.getTitle(), newPriority);

                            if (changed) {
                                System.out.println("Priorty Updated");
                            }
                            else {
                                System.out.println("Could not update priority");
                            }
                            currentPage = 0;
                        } catch (Exception e) {
                            System.out.println("Invalid priority input");
                        }
                    } 
                    else {
                        System.out.println("Invalid movie number");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid movie number");
                }
            }
            // Sort
            else if (userInput.equalsIgnoreCase("S")) {
                ascending = !ascending;
                currentPage = 0;
            }
            // Menu
            else if (userInput.equalsIgnoreCase("B")) {
                menu = false;
            }
        }
    }
}