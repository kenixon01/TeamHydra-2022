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
import java.net.URL;
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
        String userOption = console.menuInputValidator(new String[]{"y", "n"});
        if (!userOption.equalsIgnoreCase("y")) {
            exitGame();
        }
        consoleView.gameDescription();
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
        System.out.println("new save game");
        try {
            if(file.exists() && file.createNewFile()) {
                System.out.println("file created");
            }
            else{
                System.out.println("file exits and overridden");
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
        //placeholder comment for save game functionality
        try {
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

    /**
     * Loads player's last save game and prints a
     * confirmation message in the console
     *
     * @author Khamilah Nixon
     */
    public void continueGame() {
        createNewGame = false;
        //placeholder comment for load game functionality
        consoleView.continueGame();
        consoleView.print("");
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
        String userOption = "";
        if(createNewGame) {
            while (!validMenuOption) {
                characterView = new CharacterView();
                characterView.characterSelect(); // TODO call by character controller

                userOption = console.menuInputValidator(new String[]{"1", "2", "3", "4"});
                switch (userOption) {
                    case "1", "4", "2", "3" -> validMenuOption = true;
                    default -> invalidCommand("character menu");
                }
                if(validMenuOption) {
                    character = Character.loadCharacterData(Integer.parseInt(userOption));
                    consoleView.verifyCharacter(character); // TODO call by character controller
                    userOption = console.menuInputValidator(new String[]{"y","n"});
                    if(userOption.equalsIgnoreCase("n")) {
                        validMenuOption = false;
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
        if(createNewGame) {
            // Get room connections
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
                String[] roomDetails = roomReader.getHashMap().get(i);

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

                //TODO remove after testing
                String[] keysRequired =
                        {"Key Piece 1", "Key Piece 2", "Key Piece 3", "Key Piece 4"};

                Room room1 = null;
                String roomName = roomDetails[1];
                boolean isLocked = Boolean.parseBoolean(roomDetails[2]);
                // TODO remove after testing because this is hardcoded
                if (i == 20) {
                    room1 = new Room(i, roomName, roomDescriptions,
                            isLocked, roomConnections, inventoryController, keysRequired);
                } else {
                    room1 = new Room(i, roomName, roomDescriptions,
                            isLocked, roomConnections, inventoryController, null);
                }

                // TODO remove after testing
                // displays the item added to the room
                if (item != null) {
                    System.out.println(item.get_itemName()
                            + " added to " + room1.getRoomName());
                }
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
        while (!console.getInput().equalsIgnoreCase("exit")) {
            boolean validCommand = false;
            room = Room.getRoom(characterController.getModel().getRoomNumber());
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

    /**
     * @author Khamilah Nixon
     * @param monster a monster in current room
     */
    private void monsterSpawn(Monster monster) {
        monsterController.monsterInfo(character.getRoomNumber());
        consoleView.print("");
        BattleView battleView = new BattleView();
        Battle battle = new Battle(characterController.getModel(), monster);
        battleController = new BattleController(battle, battleView);
    }

    /**
     * @author Khamilah Nixon
     * @param monster a monster in current room
     * @param roomID current room number
     */
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
    private boolean isValidGameCommand() {
        switch (console.inputValidator()) {
            case "stats" -> characterController.printPlayerDetails();
            case "use" -> itemController.useItem(characterController.getModel());
            case "equip" -> characterController.equip(console.getItem());
            case "pickup" -> {}
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

    /*private void beginPuzzle() {
        while(!console.getInput().equalsIgnoreCase(""))
    }*/

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
