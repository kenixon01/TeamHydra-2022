import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Author: Brian Smithers
 */
public class Entry {
    public static void main(String[] args) {
        System.out.println("Do you want to start the game? (y) (n)");
        try {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("y")) {
                Console console = new Console();
                ConsoleView consoleView = new ConsoleView();
                ConsoleController consoleController =
                        new ConsoleController(console, consoleView);
                consoleController.enterCommand();
            }
            else {
                System.out.println("Exiting game");
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input.");
        }
    }
}
