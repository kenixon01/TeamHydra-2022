package Character;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Item.Item;
import Room.Room;
import Room.RoomReader;

import java.util.*;

/**
 * Author: Brian Smithers
 * Co Authors: David Khamilah
 */

public class Character {
    private int roomNumber;

    private final String id;
    private final String name;
    private final LinkedList<Item> playerItemInventory;
    private final String description;
    private int maxHitPoints;
    private int currentHitPoints;
    private double dodgeChance;
    private double criticalHitChance;
    private int damage;

//    private TreeMap<Integer,Room> rooms = RoomReader.roomReader();
//    private String location_ = RoomReader.roomReader().firstEntry().getValue().getName();

    private double blockChance;

    public Character(String id, String name, LinkedList<Item> playerItemInventory,
                     String description, int maxHitPoints, double dodgeChance, int damage) {
        this.id = id;
        this.name = name;
        this.playerItemInventory = playerItemInventory;
        this.description = description;
        this.maxHitPoints = maxHitPoints;
        this.currentHitPoints = maxHitPoints;
        this.dodgeChance = dodgeChance;
        this.damage = damage;
    }

    // Create new Character object with choice 1 - 4.
    public static Character loadCharacterData(int number) {
        String characterFilePath;
        String startingItemFilePath;
        String startingItemDescriptionFilePath;
        String characterDescriptionFilePath;
        CharacterReader cr = null;
        switch (number) {
            case 1 -> {
                characterFilePath = "src/Character/CharacterTextFiles/Character_01/CHAR_01.txt";
                startingItemFilePath = "src/Character/CharacterTextFiles/Character_01/" +
                        "FlintLockPistolAndDagger.txt";
                startingItemDescriptionFilePath = "src/Character/CharacterTextFiles/Character_01/" +
                        "CHAR_01_Weapon_Description.txt";
                characterDescriptionFilePath = "src/Character/CharacterTextFiles/Character_01/" +
                        "CHAR_01_Description.txt";
                cr = new CharacterReader(characterFilePath, startingItemFilePath,
                        characterDescriptionFilePath, startingItemDescriptionFilePath);
                cr.read();
            }
            case 2 -> {
                characterFilePath = "src/Character/CharacterTextFiles/Character_02/CHAR_02.txt";
                startingItemFilePath = "src/Character/CharacterTextFiles/Character_02/" +
                        "DaggerOfDragons.txt";
                startingItemDescriptionFilePath = "src/Character/CharacterTextFiles/Character_02/" +
                        "CHAR_02_Weapon_Description.txt";
                characterDescriptionFilePath = "src/Character/CharacterTextFiles/" +
                        "Character_02/CHAR_02_Description.txt";
                cr = new CharacterReader(characterFilePath, startingItemFilePath,
                        characterDescriptionFilePath, startingItemDescriptionFilePath);
                cr.read();
            }
            case 3 -> {
                characterFilePath = "src/Character/CharacterTextFiles/Character_03/CHAR_03.txt";
                startingItemFilePath = "src/Character/CharacterTextFiles/Character_03/" +
                        "ShadowBow.txt";
                startingItemDescriptionFilePath = "src/Character/CharacterTextFiles/Character_03/" +
                        "CHAR_03_Weapon_Description.txt";
                characterDescriptionFilePath = "src/Character/CharacterTextFiles/Character_03/" +
                        "CHAR_03_Description.txt";
                cr = new CharacterReader(characterFilePath, startingItemFilePath,
                        characterDescriptionFilePath, startingItemDescriptionFilePath);
                cr.read();
            }
            case 4 -> {
                characterFilePath = "src/Character/CharacterTextFiles/Character_04/CHAR_04.txt";
                startingItemFilePath = "src/Character/CharacterTextFiles/Character_04/" +
                        "WarhammerOfTheRevenant.txt";
                startingItemDescriptionFilePath = "src/Character/CharacterTextFiles/Character_04/" +
                        "CHAR_04_Weapon_Description.txt";
                characterDescriptionFilePath = "src/Character/CharacterTextFiles/Character_04/" +
                        "CHAR_04_Description.txt";
                cr = new CharacterReader(characterFilePath, startingItemFilePath,
                        characterDescriptionFilePath, startingItemDescriptionFilePath);
                cr.read();
            }
            default -> {
            } // TODO make default
        }
        assert cr != null;
        return cr.getCharacter();
    }

//    public String getLocation() {
//        return location_;
//    }
    /**
     * Help method that will display a
     * list of commands the player can use.
     * @author David Huber and Khamilah Nixon
     * @return a list of commands
     */
    public String help() {
        StringBuilder commandList = new StringBuilder();
        BufferedReader file = null;
        try {
            file = new BufferedReader(new FileReader("src/Character/CharacterTextFiles/CommandList.txt"));
            while(file.ready()) {
                commandList.append(file.readLine()).append("\n");
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return commandList.toString();
    }

    /**
     * Author: Brian Smithers
     */
    public boolean traverseRooms(String direction) {
        // Copy room object for the players current room
        Room currentRoom = Objects.requireNonNull(Room.getRoom(getRoomNumber()));

        // Get connections from copied room object
        String[][] roomConnections = currentRoom.getRoomConnections();

        // Do not iterate if direction is "-1"
        boolean nextPass = direction.equalsIgnoreCase("-1");

        for (String[] room : roomConnections) {
            if (!nextPass) {
                for (int j = 0; j < room.length; j++) {
                    // Look for direction (N, S, E, W)
                    if (room[j].equalsIgnoreCase(direction)) {
                        // Get players new room number and assign it to player
                        int newRoomNumber = Integer.parseInt(room[j - 1]);
                        setRoomNumber(newRoomNumber);
                        nextPass = true; // Stop iterating
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /*
    public void move(String direction, HashMap<String, Room> rooms) {
        direction = direction.toLowerCase();
        Room current = rooms.get(location_);

        String[] temp = current.getNeighbors();

        Room next = null;

        if (direction.equals("north")) {
            if (!temp[0].equals("-")) {//if there is a room in said direction
                next = rooms.get(temp[0]);
                if(!next.isLocked()) {
                    location_ = temp[0];
                }
                else
                {
                    System.out.println("Room locked");
                }
            } else {
                System.out.println("Sorry, cannot go this way, try again!");
            }
        } else if (direction.equals("south")) {
            if (!temp[1].equals("-")) {//if there is a room in said direction
                next = rooms.get(temp[1]);
                if(!next.isLocked()) {
                    location_ = temp[1];
                }
                else
                {
                    System.out.println("Room locked");
                }
            } else {
                System.out.println("Sorry, cannot go this way, try again!");
            }
        } else if (direction.equals("east")) {
            if (!temp[2].equals("-")) { //if there is a room in said direction
                next = rooms.get(temp[2]);
                if(!next.isLocked()) {
                    location_ = temp[2];
                }
                else
                {
                    System.out.println("Room locked");
                }

            } else {
                System.out.println("Sorry, cannot go this way, try again!");
            }
        } else if (direction.equals("west")) {
            if (!temp[3].equals("-")) {//if there is a room in said direction
                next = rooms.get(temp[3]);
                if(!next.isLocked()) {
                    location_ = temp[3];
                }
                else
                {
                    System.out.println("Room locked");
                }
            } else {
                System.out.println("Sorry, cannot go this way, try again!");
            }
        } else { //else
            System.out.println("Sorry, not valid direction, try again!");
        }
    }

     */

    @Override
    public String toString() {
        return "id: " + id + "\n" + "name: " + name + "\n" +
                "description: " + description +
                "max hit points: " + maxHitPoints + "\n" +
                "current hit points: " + currentHitPoints + "\n" +
                "dodge chance: " + dodgeChance + "\n" +
                "critical hit chance: " + criticalHitChance + "\n" +
                "damage: " + damage + "\n" +
                "location: " + Room.getRoom(roomNumber).getRoomDescription() + "\n" +
                "block chance: " + blockChance + "\n";
    }

    private Scanner scanner() {
        return new Scanner(System.in);
    }

    public int scanUserInput() {
        return scanner().nextInt();
    }

    public String getDescription() {
        return description;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public int getHp() {
        return maxHitPoints;
    }

    public void setHp(int hitPoints) {
        this.maxHitPoints = hitPoints;
    }

    public double getCriticalHitChance() {
        return criticalHitChance;
    }

    public double getBlockChance() {
        return blockChance;
    }

    public int getAttack() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Item> getPlayerItemInventory() {
        return playerItemInventory;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getId() {
        return id;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

//    public String getLocation_() {
//        return location_;
//    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public void setCurrentHitPoints(int currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
    }

    public void setDodgeChance(double dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public void setCriticalHitChance(double criticalHitChance) {
        this.criticalHitChance = criticalHitChance;
    }

//    public void setLocation_(String location_) {
//        this.location_ = location_;
//    }

    public void setBlockChance(double blockChance) {
        this.blockChance = blockChance;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}

class CharacterItem {
    private String itemId;
    private String itemName;
    private String itemEffectiveness;
    private String description;
    private String location;

    public CharacterItem(String itemId, String itemName, String itemEffectiveness, String description,
                         String location) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemEffectiveness = itemEffectiveness;
        this.description = description;
        this.location = location;
    }
}

class Tester {
    public static void main(String[] args) {
        Character testChar = Character.loadCharacterData(3);
        System.out.println(testChar);
    }
}