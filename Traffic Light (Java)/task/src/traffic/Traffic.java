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
        String menu;
        do {
            System.out.println("Menu:\n" +
                    "1. Add\n" +
                    "2. Delete\n" +
                    "3. System\n" +
                    "0. Quit");
            menu = scanner.nextLine();
            printSelection(menu, scanner);

        } while (!"0".equals(menu));
        System.out.println("Bye!");
    }
    private void printSelection(String menu, Scanner scanner) {
        int number = 0;
        try {
            number = Integer.parseInt(menu);
        } catch (NumberFormatException e) {
            System.out.print("Incorrect option");
            scanner.nextLine();
            clearTerminal();
        }
        if (number != 0) {
            switch (number) {
                case 1:
                    System.out.print("Road added");
                    break;
                case 2:
                    System.out.print("Road deleted");
                    break;
                case 3:
                    System.out.print("System opened");
                    break;
                default:
                    System.out.print("Incorrect option");
                    break;
            }
            scanner.nextLine();
            clearTerminal();
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
