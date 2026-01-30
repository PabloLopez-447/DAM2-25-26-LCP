import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        displayMenu();
        int choice = scanner.nextInt();
        
        do {
            switch (choice) {
                case 1:
                    System.out.println("You selected Option One.");
                    break;

                case 2:
                    System.out.println("You selected Option Two.");
                    break;
                default:
                    break;
            }
            displayMenu();
            choice = scanner.nextInt();
        } while (choice != 3);
    }

    public static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Option One");
        System.out.println("2. Option Two");
        System.out.println("3. Exit");
        System.out.print("Please select an option: ");
    }
}
