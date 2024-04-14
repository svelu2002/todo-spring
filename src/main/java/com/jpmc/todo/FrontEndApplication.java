package com.jpmc.todo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FrontEndApplication {
    private static void displayMenu() {
        String menu = """
                Menu
                1.  Add product to inventory
                2.  Display available products
                3.  Add customer
                4.  Place an order
                5.  Calculate total price of an order
                6.  Display customer information and orders
                7.  Exit
                """;
        Scanner scanner = new Scanner(System.in);
        boolean exitOption;
        int selectedOption;
        do {
            exitOption = false;
            System.out.println(menu);
            System.out.print("Enter a valid option: ");
            try {
                selectedOption = scanner.nextInt();
                switch (selectedOption) {
                    case 1:
                        addProductToInventory();
                        break;
                    case 2:
                        // displayAvailableProducts();
                        break;
                    case 3:
                        // addCustomer();
                        break;
                    case 4:
                        // placeAnOrder();
                        break;
                    case 5:
                        // calculateTotalPrice();
                        break;
                    case 6: // displayCustomerOrderInformation();
                        break;
                    case 7: // exit
                        exitOption = true;
                        break;
                    default:
                        System.out.println("Invalid selection.  Please enter a valid option");
                        scanner.nextLine();
                        continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid selection.  Please enter a valid option");
                scanner.nextLine();
                continue;
            }
            if (!exitOption)
                exitOption = promptForContinuation(scanner);
        } while (!exitOption);
    }

    private static boolean promptForContinuation(Scanner scanner) {
        boolean innerExitOption;
        boolean exitOption;
        String cont;
        scanner.nextLine(); // flush
        do {
            innerExitOption = false;
            exitOption = false;
            cont = "";
            System.out.print("Do you want to continue: (Y/n): ");
            cont = scanner.nextLine();
            if (cont.equalsIgnoreCase("n") || cont.equalsIgnoreCase("no")) {
                exitOption = true;
                innerExitOption = true;
            } else if (cont.equalsIgnoreCase("y") || cont.equalsIgnoreCase("yes")) {
                innerExitOption = true;
            } else {
                System.out.println("Invalid input.  Please enter y/n.");
            }
        } while (!innerExitOption);
        return exitOption;
    }

    private static void addProductToInventory() {
        System.out.println("Product added successfully.");
    }

    public static void main(String[] args) {
        displayMenu();
    }
}
