package Entry;


import Console.Console;
import Console.ConsoleController;
import Console.ConsoleView;

import java.util.InputMismatchException;

/**
 * Author: Brian Smithers
 */
public class Entry {

    //Brian Smithers and Khamilah Nixon
    public static void main(String[] args) {
        // TODO ask Jason how the item's initialize. When should it init?

        Console console = new Console();
        ConsoleView consoleView = new ConsoleView();
        ConsoleController consoleController = new ConsoleController(console, consoleView);

        try {
            consoleController.startGame();

            //main menu displays on console
            //enter start, continue, or exit on main menu
            consoleController.mainMenu();

            //character select menu displays on console
            //player can select and verify their character
            consoleController.characterSelect();

            //load rooms
            consoleController.createRooms();


            /*
              allows player to enter commands once the game
              environment and first room loads
            */
            consoleController.enterCommand();
        }
        catch (InputMismatchException e) {
            consoleController.invalidCommand();
        }
    }
}
