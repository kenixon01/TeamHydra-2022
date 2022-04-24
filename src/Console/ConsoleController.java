package Console;

import Character.CharacterController;
import Battle.BattleController;
import Item.ItemController;
import Monster.MonsterController;
import Puzzle.PuzzleController;
import Room.RoomController;

public class ConsoleController {
    private BattleController battleController;
    private CharacterController characterController;
    private ItemController itemController;
    private MonsterController monsterController;
    private PuzzleController puzzleController;
    private RoomController roomController;
    private Console console;
    private ConsoleView consoleView;

    public ConsoleController(Console model, ConsoleView view) {
        this.console = model;
        this.consoleView = view;
    }

    /**
     * This method will ask the player if they want to start the game.
     * Then, it will verify if the player enters "y" or "n"
     * If the player enters "y," then the main menu will appear on the
     * console.  Otherwise, the game will end.
     * @author Khamilah Nixon
     */
    public void startGame() {
        consoleView.startGame();
        String userOption = console.menuInputValidator(new String[]{"y","n"});
        if(!userOption.equalsIgnoreCase("y")) {
            exitGame();
        }
        consoleView.gameDescription();
    }

    /**
     * Displays an invalid command message in the console
     * @author Khamilah Nixon
     */
    public void invalidCommand() {
        consoleView.invalidCommand();
    }

    /**
     * Displays an exit game message and terminates the program
     * @author Khamilah Nixon
     */
    public void exitGame() {
        consoleView.exitGame();
        System.exit(0);
    }

    /**
     * Saves player's current progress and prints a
     * confirmation message in the console
     * @author Khamilah Nixon
     */
    public void saveGame() {
        //placeholder comment for save game functionality
        consoleView.saveGame();
    }

    /**
     * Loads player's last save game and prints a
     * confirmation message in the console
     * @author Khamilah Nixon
     */
    public void continueGame() {
        //placeholder comment for load game functionality
        consoleView.continueGame();
    }

    /**
     * Displays the main menu in the console and allows the
     * user to select their menu choice
     * @author Khamilah Nixon
     */
    public void mainMenu() {
        //displays main menu in console
        consoleView.mainMenu();
        String userOption = console.menuInputValidator(
                new String[]{"start","continue","exit"}
        ).toLowerCase();
        switch (userOption) {
            case "start" -> {
                startGame();
            }
            case "continue" -> {
                continueGame();
            }
            case "exit" -> {
                exitGame();
            }
        }
    }

    /**
     * Displays the character selection in the console
     * and allows the user to select their character choice
     * @author Khamilah Nixon
     */
    public void characterSelect() {
        //call the CharacterView characterSelect
        String userOption = console.menuInputValidator(
                new String[]{"1","2","3","4"}
        );
        //placeholder for character selection functionality
    }

    /**
     * Allows player to enter commands during gameplay
     * @author Brian Smithers and Khamilah Nixon
     */
    public void enterCommand() {
        while (!console.getInput().equalsIgnoreCase("exit")) {
            console.enterCommand();

            switch (console.inputValidator()) {


                default -> invalidCommand();
            }
        }
        exitGame();
    }
}
