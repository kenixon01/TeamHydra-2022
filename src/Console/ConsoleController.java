package Console;

import Battle.*;
import Character.Character;
import Inventory.*;
import Item.*;
import Room.*;
import Character.*;
import Monster.*;
import Puzzle.*;

import java.io.IOException;
import java.util.List;

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
    public void characterSelect() throws IOException {
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
        character.setRoomNumber(1);
//        itemController.addToInventory("-" + userOption, character);
        characterController = new CharacterController(character, characterView);

        monsterView = new MonsterView();
        monsterController = new MonsterController(Monster.createMonsters(),monsterView);

        puzzleView = new PuzzleView();
        puzzleController = new PuzzleController(puzzleView);

    }

    /**
     * Author: Brian Smithers
     */
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
        for (int i = 1; i < numberOfRooms + 1; i++) {
            String[][] roomConnections = readRoomConnections.getHashMap().get(i);
            String roomDescriptions = readRoomDescription.getRoomDescriptionHashMap().get(i);
            String[] roomDetails = roomReader.getHashMap().get(i);

            String roomName = roomDetails[1];
            boolean isLocked = Boolean.parseBoolean(roomDetails[2]);
            Room room1 = new Room(i, roomName, roomDescriptions,
                    isLocked, roomConnections, new InventoryController(
                            new Inventory(), new InventoryView()));
            Room.addRoom(room1);
        }
        // Make model and view for controller
        room = Room.getRoom(1);
        roomView = new RoomView();
        roomController = new RoomController(room, roomView);

        // TODO if/else to verify if a room is locked
        roomController.printRoomDescription();
    }

    /**
     * Allows player to enter commands during gameplay
     * @author Brian Smithers and Khamilah Nixon
     */
    public void enterCommand() {
        while (!console.getInput().equalsIgnoreCase("exit")) {
            boolean validCommand = false;
            room = Room.getRoom(character.getRoomNumber());
            roomController.setModel(room);
            int roomID = characterController.getModel().getRoomNumber();
            if (monsterController.getModel().get(roomID) != null && monsterController.getModel().get(roomID).getHp() > 0) {
                System.out.println();
                monsterController.monsterDescription(roomID);
                battleView = new BattleView();
                battle = new Battle(characterController.getModel(),monsterController.getModel().get(roomID));
                battleController = new BattleController(battle,battleView);
            }

            //load puzzles
            /*
            if(puzzleController.getModel() != null){

            }

             */
            System.out.println();
            while (!validCommand) {
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
            case "equip" -> characterController.equip(console.getItem());
            case "unequip" -> characterController.unEquipItem(console.getItem());
            case "north", "south", "east", "west" -> {
                characterController.move(console.inputValidator());
            }
            case "attack" -> {
                if (battleController.getModel().getMonster().getHp() >= 0) {
                    battleController.printBattleDetails(
                            characterController.getModel().getInventoryController().
                                    getItemInventory(), false, false);
                }
                battleMessages();
            }
            case "block" -> {
                if (battleController.getModel().getMonster().getHp() > 0 ) {
                    battleController.printBattleDetails(characterController.getModel().getInventoryController().
                                    getItemInventory(), false, true);
                }
                battleMessages();
            }
            case "location" -> characterController.printPlayerLocation();
            case "inventory" -> characterController.printInventory();
            case "save" -> System.out.println("save");
            case "resume" -> System.out.println("resume");
            case "dodge" -> {
                if(battleController.getModel().getMonster().getHp() > 0) {
                    battleController.printBattleDetails(characterController.getModel().getInventoryController().
                            getItemInventory(), true, false);
                }
                battleMessages();
            }
            case "hint" -> {
                int roomID = characterController.getModel().getRoomNumber();
                List<Puzzle> puzzleList = puzzleController.getPuzzles().get(roomID + "");
                int chRoomID = characterController.getModel().getRoomNumber();
                for(Puzzle pz : puzzleList) {
                    if(pz.getRoom().equalsIgnoreCase(chRoomID + "")) {
                        puzzleController.puzzleHint(characterController.getModel().getRoomNumber(), pz.getId());
                    }
                }
            }
            case "help" -> characterController.printHelp();
            case "solve" -> {
                System.out.println("solve");
            }
            default -> {
                invalidCommand();
                return false;
            }
        }
        return true;
    }

    /**
     * Author: Brian and Khamilah
     */
    public void battleMessages() {
        if (battleController.getModel().getPlayer().getHp() <= 0) {
            consoleView.gameOver();
            exitGame();
        }
    }
}
