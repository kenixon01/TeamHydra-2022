package Character;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Item.Item;
import Room.Room;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Author: Brian Smithers
 * Co Authors: David Khamilah
 */

public class Character {
    private final String id;
    private final String name;
    private final LinkedList<Item> playerItemInventory;
    private final String description;
    private int hitPoints;
    private double dodgeChance;
    private double criticalHitChance;
    private int damage;
    private static String location_;
    private double blockChance;

    public Character(String id, String name, LinkedList<Item> playerItemInventory,
                     String description, int hitPoints, double dodgeChance, int damage) {
        this.id = id;
        this.name = name;
        this.playerItemInventory = playerItemInventory;
        this.description = description;
        this.hitPoints = hitPoints;
        this.dodgeChance = dodgeChance;
        this.damage = damage;
    }

    // Create new Character.Character object with choice 1 - 4.
    public static Character loadCharacterData(int number) {
        String characterFilePath;
        String startingItemFilePath;
        String characterDescriptionFilePath;
        CharacterReader cr = null;
        switch (number) {
            case 1:
                characterFilePath = "src/CharacterTextFiles/Character_01/CHAR_01.txt";
                startingItemFilePath = "src/CharacterTextFiles/Character_01/" +
                        "FlintLockPistolAndDagger.txt";
                characterDescriptionFilePath = "src/CharacterTextFiles/Character_01/" +
                        "CHAR_01_Description.txt";
                cr = new CharacterReader(characterFilePath, startingItemFilePath,
                        characterDescriptionFilePath);
                cr.read();
                break;
            case 2:
                characterFilePath = "src/CharacterTextFiles/Character_02/CHAR_02.txt";
                startingItemFilePath = "src/CharacterTextFiles/Character_02/" +
                        "DaggerOfDragons.txt";
                characterDescriptionFilePath = "src/CharacterTextFiles/" +
                        "Character_02/CHAR_02_Description.txt";
                cr = new CharacterReader(characterFilePath, startingItemFilePath,
                        characterDescriptionFilePath);
                cr.read();
                break;
            case 3:
                characterFilePath = "src/CharacterTextFiles/Character_03/CHAR_03.txt";
                startingItemFilePath = "src/CharacterTextFiles/Character_03/" +
                        "ShadowBow.txt";
                characterDescriptionFilePath = "src/CharacterTextFiles/Character_03/" +
                        "CHAR_03_Description.txt";
                cr = new CharacterReader(characterFilePath, startingItemFilePath,
                        characterDescriptionFilePath);
                cr.read();
                break;
            case 4:
                characterFilePath = "src/CharacterTextFiles/Character_04/CHAR_04.txt";
                startingItemFilePath = "";
                characterDescriptionFilePath = "src/CharacterTextFiles/Character_04/" +
                        "CHAR_04_Description.txt";
                cr = new CharacterReader(characterFilePath, startingItemFilePath,
                        characterDescriptionFilePath);
                cr.read();
                break;
            default: // TODO make default
        }
        return cr.getCharacter();
    }
    //TODO Character Location
    public void getLocation(HashMap<String, Room> rooms)
    {
        Room currentLocation = rooms.get(location_);
        System.out.println(currentLocation);
    }
    /**
     * Help method that will display a
     * list of commands the player can use.
     * @author David Huber and Khamilah Nixon
     * @return a list of commands
     */
    public String help()
    {
        StringBuilder commandList = new StringBuilder();
        BufferedReader file = null;
        try {
            file = new BufferedReader(new FileReader("list_of_commands.txt"));
            while(file.ready()) {
                commandList.append(file.readLine()).append("\n");
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return commandList.toString();
    }
    /**
     * The move method will take the user's input
     * and allow them to move accordingly
     * @author David Huber
     */
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
                    System.out.println("Room.Room locked");
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
                    System.out.println("Room.Room locked");
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
                    System.out.println("Room.Room locked");
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
                    System.out.println("Room.Room locked");
                }
            } else {
                System.out.println("Sorry, cannot go this way, try again!");
            }
        } else { //else
            System.out.println("Sorry, not valid direction, try again!");
        }

    }

    @Override
    public String toString() {
        return "Character.Character{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", playerItemInventory=" + playerItemInventory +
                ", description='" + description + '\'' +
                ", hitPoints=" + hitPoints +
                ", dodgeChance=" + dodgeChance +
                ", criticalHitChance=" + criticalHitChance +
                ", damage=" + damage +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public int getHp() {
        return hitPoints;
    }

    public void setHp(int hitPoints) {
        this.hitPoints = hitPoints;
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
        Character testChar = Character.loadCharacterData(4);
        System.out.println(testChar);
    }
}
