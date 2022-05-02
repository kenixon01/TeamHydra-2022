package Console;

import Battle.*;
import Character.Character;
import Inventory.*;
import Item.*;
import Room.*;
import Character.*;
import Monster.*;
import Puzzle.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Authors: Brian Smithers and Khamilah Nixon
 */
public class ConsoleController {
    private BattleController battleController;
    private CharacterController characterController;
    private Character character;
    private CharacterView characterView;

    private ItemController itemController;

    private MonsterController monsterController;

    private PuzzleController puzzleController;

    private RoomController roomController;
    private Room room;

    private Console console;
    private ConsoleView consoleView;

    private File file = new File("save.dat");

    private boolean createNewGame;
    private boolean gameRunning = false;

    private ObjectOutputStream writeFile;
    private ObjectInputStream readFile;

    public ConsoleController(Console console, ConsoleView consoleView) {
        this.console = console;
        this.consoleView = consoleView;
        try{
            writeFile = new ObjectOutputStream(new FileOutputStream(file, true));
            readFile = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * This method will ask the player if they want to start the game.
     * Then, it will verify if the player enters "y" or "n"
     * If the player enters "y," then the main menu will appear on the
     * console.  Otherwise, the game will end.
     *
     * @author Khamilah Nixon
     */
    public void startup() {
        consoleView.startGame();
        while(true) {
            String userOption = console.menuInputValidator(new String[]{"y", "n"});
            if(userOption.equalsIgnoreCase("y")) {
                consoleView.gameDescription();
                break;
            }
            if(userOption.equalsIgnoreCase("n")) {
                exitGame();
            }
            invalidCommand("");
        }
    }

    /**
     * Displays an invalid command message in the console
     *
     * @author Khamilah Nixon
     */
    public void invalidCommand(String commandCategory) {
        consoleView.invalidCommand(commandCategory);
    }

    /**
     * Displays an exit game message and terminates the program
     *
     * @author Khamilah Nixon
     */
    public void exitGame() {
        consoleView.exitGame();
        System.exit(0);
    }

    public void startGame() {
        createNewGame = true;
        try {
            if(file.exists() && file.createNewFile()) {
                consoleView.saveGameCreated();
            }
            if(gameRunning) {
                characterSelect();
                createRooms();
                enterCommand();
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Saves player's current progress and prints a
     * confirmation message in the console
     *
     * @author Khamilah Nixon
     */
    public void saveGame() {
        if(characterController.getModel().getRoomNumber() >= 21) {
            try {
                writeFile = new ObjectOutputStream(new FileOutputStream(file));
                readFile = new ObjectInputStream(new FileInputStream(file));
                writeFile.writeObject(characterController);
                writeFile.writeObject(roomController);
                writeFile.writeObject(monsterController);
                writeFile.writeObject(battleController);
                writeFile.writeObject(puzzleController);
                HashMap<Integer,Room> listOfRooms = Room.listOfRooms;
                writeFile.writeObject(listOfRooms);
                writeFile.writeObject(character);
            }catch (IOException io) {
                io.printStackTrace();
            }
            consoleView.saveGame();
        }
        else {
            consoleView.cannotSaveGame();
        }
    }

    /**
     * Loads player's last save game and prints a
     * confirmation message in the console
     *
     * @author Khamilah Nixon
     */
    public void continueGame() {
        try {
            if(file.length() > 4 && !file.createNewFile()) {
                createNewGame = false;
                consoleView.continueGame();
                consoleView.print("");
                try {
                    if(gameRunning) {
                        characterSelect();
                        createRooms();
                        enterCommand();
                    }
                }catch (IOException io) {
                    io.printStackTrace();
                }
            }else {
                consoleView.fileNotFound();
                createNewGame = true;
                try {
                    consoleView.saveGameCreated();
                    characterSelect();
                    createRooms();
                    enterCommand();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    /**
     * Displays the main menu in the console and allows the
     * user to select their menu choice
     *
     * @author Khamilah Nixon
     */
    public void mainMenu() {
        //displays main menu in console
        consoleView.mainMenu();
        boolean validMenuOption = false;
        while (!validMenuOption) {
            String userOption = console.menuInputValidator(
                    new String[]{"start", "continue", "exit", "help"}
            ).toLowerCase();
            switch (userOption) {
                case "start" -> {
                    startGame();
                    validMenuOption = true;
                }
                case "continue" -> {
                    continueGame();
                    validMenuOption = true;
                }
                case "exit" -> {
                    try{
                        writeFile.close();
                        readFile.close();
                    }catch (IOException io) {
                        io.printStackTrace();
                    }
                    exitGame();
                    validMenuOption = true;
                }
                default -> invalidCommand("menu");
            }
        }
    }

    /**
     * Displays the character selection in the console
     * and allows the user to select their character choice
     *
     * @author Khamilah Nixon, Brian Smithers, Jayson Dasher
     */
    public void characterSelect() throws IOException {
        ItemView itemView = new ItemView();
        itemController = new ItemController(new ItemReader().CreateItems(), itemView);

        boolean validMenuOption = false;
        if(createNewGame) {
            while (!validMenuOption) {
                characterView = new CharacterView();
                characterView.characterSelect(); // TODO call by character controller

                String characterOption = console.menuInputValidator(new String[]{"1", "2", "3", "4"});
                switch (characterOption) {
                    case "1", "4", "2", "3" -> validMenuOption = true;
                    default -> invalidCommand("character menu");
                }
                if(validMenuOption) {
                    character = Character.loadCharacterData(Integer.parseInt(characterOption));
                    consoleView.verifyCharacter(character); // TODO call by character controller
                    while(true) {
                        String verifyCharacterOption = console.menuInputValidator(new String[]{"y", "n"});
                        if(verifyCharacterOption.equalsIgnoreCase("y")) {
                            consoleView.gameDescription();
                            break;
                        }
                        if(!verifyCharacterOption.equalsIgnoreCase("n")) {
                            invalidCommand("");
                        }
                    }
                }
            }
            consoleView.print("");
            character.setRoomNumber(1);
            characterController = new CharacterController(character, characterView);

            MonsterView monsterView = new MonsterView();
            monsterController = new MonsterController(Monster.createMonsters(), monsterView);

            PuzzleView puzzleView = new PuzzleView();
            puzzleController = new PuzzleController(puzzleView, character);
        }
        else {
            try {
                characterController = (CharacterController) readFile.readObject();
                roomController = (RoomController) readFile.readObject();
                monsterController = (MonsterController) readFile.readObject();
                battleController = (BattleController) readFile.readObject();
                puzzleController = (PuzzleController) readFile.readObject();
                HashMap<Integer,Room> rooms = (HashMap<Integer, Room>) readFile.readObject();
                Room.listOfRooms.clear();
                for(Room r : rooms.values()) {
                    Room.addRoom(r);
                }
                character = (Character) readFile.readObject();

            } catch (IOException | ClassNotFoundException io) {
                io.printStackTrace();
            }
        }
    }

    /**
     * Author: Brian Smithers
     */
    public void createRooms() {
        // Get room connections
        if(createNewGame) {
            ReadRoomConnections readRoomConnections =
                    new ReadRoomConnections("src/Room/RoomTextFile/RoomConnections.txt");
            readRoomConnections.read();

            // Get room descriptions
            ReadRoomDescription readRoomDescription =
                    new ReadRoomDescription("src/Room/RoomTextFile/RoomDescriptions.txt");
            readRoomDescription.read();

            // Get room name and lock status
            RoomReader roomReader = new RoomReader("src/Room/RoomTextFile/Rooms_1.txt");
            roomReader.read();

            // Get all items
            ItemReader readItems = new ItemReader();
            Map<String, List<Item>> roomItems = readItems.CreateItems();

            int numberOfRooms = readRoomDescription.getRoomNumber();
            for (int i = 1; i < numberOfRooms + 1; i++) {
                String[][] roomConnections = readRoomConnections.getHashMap().get(i);
                String roomDescriptions = readRoomDescription.getRoomDescriptionHashMap().get(i);
                String[] roomDetails = roomReader.getRoomReaderRoomDetailsHashMap().get(i);
                String[] roomKeysToUnlockRoom = roomReader.getRoomReaderRoomKeysToUnlockRoomHashMap().get(i);

                InventoryController inventoryController =
                        new InventoryController(new Inventory(), new InventoryView());

                // Put items into their respective rooms
                Item item = null;
                boolean nextPass = true;
                while (nextPass) {
                    List<Item> itemList = roomItems.get(String.valueOf(i));
                    if (itemList != null) {
                        for (Item value : itemList) {
                            item = value;
                            inventoryController.addItem(item);
                        }
                    }
                    nextPass = false;
                }

                Room room1 = null;
                String roomName = roomDetails[1];
                boolean isLocked = Boolean.parseBoolean(roomDetails[2]);
                room1 = new Room(i, roomName, roomDescriptions,
                        isLocked, roomConnections, inventoryController, roomKeysToUnlockRoom);

                Room.addRoom(room1);
            }
            // Make model and view for controller
            room = Room.getRoom(1);
            RoomView roomView = new RoomView();
            roomController = new RoomController(room, roomView);
        }

        roomController.printRoomDescription();
        consoleView.print("");
    }

    /**
     * Allows player to enter commands during gameplay
     *
     * @author Brian Smithers and Khamilah Nixon
     */
    public void enterCommand() {
        // TODO remove this after testing
        character.setCurrentHitPoints(50);
        gameRunning = true;
        while (!console.getInput().equalsIgnoreCase("exit")) {
            boolean validCommand = false;
            room = Room.getRoom(character.getRoomNumber());
            roomController.setModel(room);
            int roomID = characterController.getModel().getRoomNumber();
            Monster tempMonster = monsterController.getModel().get(roomID);
            if (tempMonster != null && !tempMonster.isLocked() && tempMonster.getHp() > 0) {
                monsterSpawn(tempMonster);
            } else if (puzzleController.getPuzzles().get(Integer.toString(roomID)) != null &&
                    !puzzleController.getPuzzles().get(Integer.toString(roomID)).get(0).getSolved()) {

                puzzleSpawn(tempMonster, roomID);
            }
            // else if item exists in room
            while (!validCommand) {
                console.enterCommand();
                validCommand = isValidGameCommand();
            }
        }
        exitGame();
    }

    private void monsterSpawn(Monster monster) {
        monsterController.monsterInfo(character.getRoomNumber());
        consoleView.print("");
        BattleView battleView = new BattleView();
        Battle battle = new Battle(characterController.getModel(), monster);
        battleController = new BattleController(battle, battleView);
    }

    private void puzzleSpawn(Monster monster, int roomID) {
        puzzleController.checkForPuzzle(roomID, character);
        if (puzzleController.getPuzzles().get(Integer.toString(roomID)).get(0).getSolved() &&
                monster != null && monster.getHp() > 0 && monster.isLocked()) {
            monster.setLocked(false);
            monsterSpawn(monster);
        }

    }

    /**
     * @return if player entered a valid command
     * @author Khamilah Nixon, Brian Smithers, Jayson Dasher
     */
    public boolean isValidGameCommand() {
        switch (console.inputValidator()) {
            // TODO pickup is for testing purposes right now
            case "pickup" -> characterController.pickUpItem(console.getItem());
            case "stats" -> characterController.printPlayerDetails();
            case "use" -> itemController.useItem(characterController.getModel());
            case "equip" -> characterController.equip(console.getItem());
            case "unequip" -> characterController.unEquipItem(console.getItem());
            case "n", "s", "e", "w" -> {
                characterController.move(console.inputValidator());
                roomController.setModel(Room.getRoom(character.getRoomNumber()));
                roomController.printRoomDescription();
            }
            case "attack" -> beginBattle(false, false);
            case "block" -> beginBattle(false, true);
            case "location" -> characterController.printPlayerLocation();
            case "inventory" -> characterController.printInventory();
            case "save" -> saveGame();
            case "menu" -> mainMenu();
            case "resume" -> continueGame();
            case "dodge" -> beginBattle(true, false);
            case "help" -> characterController.printHelp();
            default -> {
                invalidCommand("");
                return false;
            }
        }
        consoleView.print("");
        return true;
    }

    /**
     * @return if a command is a valid battle command
     * @author Khamilah Nixon and Brian Smithers
     */
    private boolean isBattleCommand() {
        switch (console.battleInputValidator()) {
            case "exit" -> exitGame();
            case "use" -> {
                // TODO add use item command
                battleController.exhaustTurn();
            }
            case "stats" -> characterController.printPlayerDetails();
            case "equip" -> {
                characterController.equip(console.getItem());
                battleController.exhaustTurn();
            }
            case "unequip" -> {
                characterController.unEquipItem(console.getItem());
                battleController.exhaustTurn();
            }
            case "help" -> characterController.printHelp();
            case "inventory" -> {
                characterController.printInventory();
            }
            case "attack" ->
                    battleController.printBattleDetails(characterController.getModel().getInventoryController().
                            getItemInventory(), false, false);

            case "block" -> battleController.printBattleDetails(characterController.getModel().getInventoryController().
                    getItemInventory(), false, true);
            case "dodge" -> battleController.printBattleDetails(characterController.getModel().getInventoryController().
                    getItemInventory(), true, false);
            default -> {
                invalidCommand("battle");
                return false;
            }
        }
        consoleView.print("");
        return true;
    }

    /**
     * Author: Brian and Khamilah
     */
    private void beginBattle(boolean dodge, boolean block) {
        if (!battleController.getModel().getMonster().isLocked()) {
            battleController.printBattleDetails(characterController.getModel().getInventoryController().
                    getItemInventory(), dodge, block);
            while (battleController.getModel().getMonster().getHp() > 0 &&
                    battleController.getModel().getPlayer().getHp() > 0) {

                boolean validCommand = false;
                while (!validCommand) {
                    console.enterCommand();
                    validCommand = isBattleCommand();
                }
            }
            if (battleController.getModel().getPlayer().getHp() <= 0) {
                exitGame();
            }
        }
    }
}
