import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MovieDatabase database = new MovieDatabase();
        int choice = 0;

        // Main menu loop
        while (choice != 4) {
            System.out.println("\n| Movie Manager |\n");
            System.out.println("1. Search Movies");
            System.out.println("2. View Watchlist");
            System.out.println("3. View Watched List");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String userInput = input.nextLine().trim();

            // Removing the period in case user inputs it.
            if (userInput.endsWith(".")) {
                userInput = userInput.substring(0, userInput.length() - 1);
            }

            try {
                choice = Integer.parseInt(userInput);
            } 
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 4");
                continue;
            }
            
            // Where the selection magic happens
            switch (choice) {
                case 1:
                    SearchMenu.open(input, database); //The way this is set up opens the search menu and all its stuff.
                    break;
                case 2:
                    System.out.println("View Watchlist option selected");
                    break;
                case 3:
                    System.out.println("View Watched list option selected");
                    break;
                case 4:
                    System.out.println("Exiting Movie Manager...");
                    break;
                default:
                    System.out.println("Invalid option. Please type any of the numbers listed above");
            }
        }

        input.close();
    }
}