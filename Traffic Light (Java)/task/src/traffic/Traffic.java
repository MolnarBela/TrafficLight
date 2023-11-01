package traffic;

import java.io.IOException;
import java.util.Scanner;

public class Traffic {
    QueueThread qThread;
    private static SystemState state;

    public static SystemState getState() {
        return state;
    }

    public void initialize() {
        state = SystemState.NOT_STARTED;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the traffic management system!");
        System.out.print("Input the number of roads: ");
        int roads = getValidNumber(scanner);
        System.out.print("Input the interval: ");
        int interval = getValidNumber(scanner);
        qThread = new QueueThread(roads, interval);
        qThread.start();
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
        qThread.setRunning(false);
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
                    System.out.print("Input road name: ");
                    String name = scanner.nextLine();
                    qThread.addRoad(name);
                    break;
                case 2:
                    qThread.deleteRoad();
                    break;
                case 3:
                    state = SystemState.SYSTEM;
                    break;
                default:
                    System.out.print("Incorrect option");
            }
            scanner.nextLine();
            state = SystemState.MENU;
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

    public static void clearTerminal() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        }
        catch (IOException | InterruptedException e) {}
    }

}
