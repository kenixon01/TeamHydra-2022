package Console;

import Battle.*;
import Character.Character;
import Item.*;
import Room.*;
import Character.*;
import Monster.*;
import Puzzle.*;

public class ConsoleController {
    private BattleController battleController;
    private Battle battle;
    private BattleView battleView;
    private CharacterController characterController;
    private Character character;
    private CharacterView characterView;

    private ItemController itemController;
    private Item item;
    private ItemView itemView;

    private MonsterController monsterController;
    private Monster monster;
    private MonsterView monsterView;

    private PuzzleController puzzleController;
    private Puzzle puzzle;
    private PuzzleView puzzleView;

    private RoomController roomController;
    private Room room;
    private RoomView roomView;

    private Console console;
    private ConsoleView consoleView;

    public ConsoleController(BattleController battleController, Battle battle, BattleView battleView, CharacterController characterController, Character character, CharacterView characterView, ItemController itemController, Item item, ItemView itemView, MonsterController monsterController, Monster monster, MonsterView monsterView, PuzzleController puzzleController, Puzzle puzzle, PuzzleView puzzleView, RoomController roomController, Room room, RoomView roomView, Console console, ConsoleView consoleView) {
        this.battleController = battleController;
        this.battle = battle;
        this.battleView = battleView;
        this.characterController = characterController;
        this.character = character;
        this.characterView = characterView;
        this.itemController = itemController;
        this.item = item;
        this.itemView = itemView;
        this.monsterController = monsterController;
        this.monster = monster;
        this.monsterView = monsterView;
        this.puzzleController = puzzleController;
        this.puzzle = puzzle;
        this.puzzleView = puzzleView;
        this.roomController = roomController;
        this.room = room;
        this.roomView = roomView;
        this.console = console;
        this.consoleView = consoleView;
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
        characterController.chooseCharacter();
        //call the CharacterView characterSelect
        String userOption = console.menuInputValidator(
                new String[]{"1","2","3","4"}
        );
        System.out.println("character selected");
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
                case "inspect" -> System.out.println("inspect");
                case "drop" -> System.out.println("drop");
                case "use" -> System.out.println("use");
                case "equip" -> System.out.println("equip");
                case "unequip" -> System.out.println("unequip");
                case "north" -> System.out.println("north");
                case "east" -> System.out.println("east");
                case "south" -> System.out.println("south");
                case "west" -> System.out.println("west");
                case "attack" -> System.out.println("attack");
                case "inventory" -> System.out.println("inventory");
                case "save" -> System.out.println("save");
                case "resume" -> System.out.println("resume");
                case "block" -> System.out.println("block");
                case "dodge" -> System.out.println("dodge");
                case "hint" -> System.out.println("hint");
                default -> invalidCommand();
            }
        }
        exitGame();
    }
}
