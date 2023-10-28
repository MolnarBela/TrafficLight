package traffic;

import java.io.IOException;
import java.util.Scanner;

public class Traffic {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the traffic management system!");
        System.out.print("Input the number of roads: ");
        int roads = getValidNumber(scanner);
        System.out.print("Input the interval: ");
        int interval = getValidNumber(scanner);
        int menu;
        do {

            System.out.println("Menu:\n" +
                    "1. Add\n" +
                    "2. Delete\n" +
                    "3. System\n" +
                    "0. Quit");
            menu = scanner.nextInt();
            printSelection(menu, scanner);
            scanner.nextLine();
        } while (menu != 0);
        System.out.println("Bye!");
    }
    private void printSelection(int number, Scanner scanner) {
        switch (number) {
            case 1:
                System.out.println("Road added");
                break;
            case 2:
                System.out.println("Road deleted");
                break;
            case 3:
                System.out.println("System opened");
                break;
            default:
                System.out.println("Incorrect option");
                scanner.nextLine();
                clearTerminal();
                break;
        }
    }

    private int getValidNumber(Scanner scanner) {
        int number = 0;
        boolean notValide = true;

        do {
            String roadsString = scanner.nextLine();
            try {
                number = Integer.parseInt(roadsString);
            } catch (NumberFormatException e) {
                System.out.print("Error! Incorrect Input. Try again: ");
                continue;
            }
            if (number < 1) {
                System.out.print("Error! Incorrect Input. Try again: ");
            } else {
                notValide = false;
            }
        } while (notValide);
        return number;
    }

    private void clearTerminal() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        }
        catch (IOException | InterruptedException e) {}
    }

}
