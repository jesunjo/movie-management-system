import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("Movie Manager\n");
            System.out.println("1. Search Movies");
            System.out.println("2. View Watchlist:");
            System.out.println("3. View Watched List:");
            System.out.println("4. Exit:");
            System.out.print("Enter your choice: ");

            String userInput = input.nextLine().trim();

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
            

            if (choice == 1) {
                System.out.println("Search Movies option selected");
            }
            else if (choice == 2) {
                System.out.println("View Watchlist option selected");
            }
            else if (choice == 3) {
                System.out.println("View Watched list option selected");
            }
            else if (choice == 4) {
                System.out.println("Exiting Movie Manager...");
            }
            else {
                System.out.println("Invalid option. Please type any of the numbers listed above"); 
            }
        }

        input.close();
    }
}