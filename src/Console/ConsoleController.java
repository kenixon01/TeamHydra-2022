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

    public ConsoleController(Console console, ConsoleView consoleView) {
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
     * @author Khamilah Nixon, Brian Smithers, Jayson Dasher
     */
    public void characterSelect() {
        itemView = new ItemView();
        itemController = new ItemController(new ItemReader().CreateItems(),itemView);
        // TODO initialize puzzle, room, monster, and battle controller

        //call the CharacterView characterSelect
        characterView = new CharacterView();
        characterView.characterSelect();
        String userOption = console.menuInputValidator(
                new String[]{"1","2","3","4"}
        );
        // Create new character object
        character = Character.loadCharacterData(Integer.parseInt(userOption));
        itemController.addToInventory("-" + userOption, character);
        characterController = new CharacterController(character, characterView);

        monsterView = new MonsterView();
        monsterController = new MonsterController(new MonsterReader().getMonsterHashMap(), monsterView);

        puzzleView = new PuzzleView();
        puzzleController = new PuzzleController(puzzleView);

        // TODO make a method for this called "createRooms"
       createRooms();

    }

    public void createRooms() {
        ReadRoomConnections readRoomConnections =
                new ReadRoomConnections("src/Room/RoomTextFile/RoomConnections.txt");
        readRoomConnections.read();
        ReadRoomDescription readRoomDescription =
                new ReadRoomDescription("src/Room/RoomTextFile/RoomDescriptions.txt");
        readRoomDescription.read();
        RoomReader roomReader = new RoomReader("src/Room/RoomTextFile/Rooms_1.txt");
        roomReader.read();

        int numberOfRooms = readRoomDescription.getRoomNumber();
    }

    /**
     * Allows player to enter commands during gameplay
     * @author Brian Smithers and Khamilah Nixon
     */
    public void enterCommand() {
        while (!console.getInput().equalsIgnoreCase("exit")) {
            boolean validCommand = false;
            roomController.printRoomDescription();
            int roomID = characterController.getModel().getRoomNumber();
            if(monsterController.getModel().get(roomID) != null) {
                System.out.println("monster exists");
            }
            while(!validCommand) {
                console.enterCommand();
                validCommand = isValidGameCommand();
            }
        }
        exitGame();
    }

    /**
     *
     * @return if player entered a valid command
     * @author Khamilah Nixon, Brian Smithers, Jayson Dasher
     */
    public boolean isValidGameCommand() {
        switch (console.inputValidator()) {
            case "use" -> {
                System.out.println("use");
                itemController.useItem(characterController.getModel());
            }
            case "equip" -> {
                System.out.println("equip");
                itemController.equipItem(characterController.getModel());
            }
            case "unequip" -> {
                System.out.println("unequip");
                itemController.equipItem(characterController.getModel());
            }
            case "north", "south", "east", "west" -> {
                characterController.move(console.inputValidator(), characterController.getModel());
            }
            case "attack" -> battleController.printBattleDetails(itemController.getModel().get("0"),false, false);
            case "block" -> battleController.printBattleDetails(itemController.getModel().get("0"),false, true);
            case "location" -> characterController.printPlayerLocation();
            case "inventory" -> characterController.printInventory();
            case "save" -> System.out.println("save");
            case "resume" -> System.out.println("resume");
            case "dodge" -> battleController.printBattleDetails(itemController.getModel().get("0"),true,false);
            case "hint" -> System.out.println("puzzle hint");
            case "help" -> characterController.printHelp();
            case "solve" -> System.out.println("solve");
            default -> {
                invalidCommand();
                return false;
            }
        }
        return true;
    }
}
