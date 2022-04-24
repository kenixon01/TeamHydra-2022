package Console;

public class ConsoleController {

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
     * Displays the main menu in the console and allows the
     * user to select their menu choice
     * @author Khamilah Nixon
     */
    public void mainMenu() {
        //displays menu in console
        consoleView.mainMenu();
        String userOption = console.menuInputValidator(
                new String[]{"start","continue","exit"}
        ).toLowerCase();

    }

    /**
     * Displays the character selection in the console
     * and allows the user to select their character choice
     * @author Khamilah Nixon
     */
    public void characterSelect() {
        consoleView.characterSelect();
        String userOption = console.menuInputValidator(
                new String[]{"1","2","3","4"}
        );
    }

    /**
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
