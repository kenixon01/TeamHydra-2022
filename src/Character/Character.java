package Character;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Inventory.*;
import Item.Item;
import Room.Room;

import java.util.*;

/**
 * Author: Brian Smithers
 * Co Authors: Khamilah
 */

public class Character {
    private int roomNumber;

    private final String id;
    private final String name;
    private final String description;
    private int maxHitPoints;
    private int currentHitPoints;
    private double dodgeChance;
    private double criticalHitChance;
    private int damage;
    private Item weapon;
    private Item wearable;

    private final InventoryController inventoryController;

    private double blockChance;

    public Character(String id, String name, InventoryController inventoryController, String description,
                     int maxHitPoints, double dodgeChance, int damage) {
        this.id = id;
        this.name = name;
        this.inventoryController = inventoryController;
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

    public String equipItem(String itemName) {
       itemName = itemName.stripTrailing();
       if (itemName.equalsIgnoreCase(weapon.get_itemName()) ||
               itemName.equalsIgnoreCase(wearable.get_itemName())) {
           return "This item is already equipped.";
       }
       else {
           for (int i = 0; i < inventoryController.getItemInventory().size(); i++) {
               if (inventoryController.getItemInventory().get(i).get_itemName()
                       .equalsIgnoreCase(itemName)) {
                   // Get item
                   Item item = inventoryController.getItemInventory().get(i);
                   if (item.get_itemType().equalsIgnoreCase("weapon")) {
                       // Add weapon to users weapon slot
                       setWeapon(item);
                       setMaxHitPoints(getMaxHitPoints() + item.get_totalHpModifier());
                       setCurrentHitPoints(getMaxHitPoints());
                       setDamage(item.get_damageValue());
                       // equip item
                       item.setEquipped(true);
                       return "Player equipped " + item.get_itemName() + "\n" +
                               "Attack points equal: " + damage + "\n";
                   }
                   // If the item is a wearable, add stat buffs.
                   if (item.get_itemType().equalsIgnoreCase("wearable")) {
                       // Increase max health if the item increases total hp
                       setMaxHitPoints(getMaxHitPoints() + item.get_totalHpModifier());
                       // If the item restores health on pick up, restore health.
                       if (item.isRestoreHealthOnPickUp()) {
                           item.setRestoreHealthOnPickUp(false); // item can't restore health again
                           setHp(getMaxHitPoints());
                       }
                       // Increase damage
                       if (item.get_damageValue() > 0) {
                           setDamage(getDamage() + item.get_damageValue());
                       }
                       // equip item
                       item.setEquipped(true);
                   }
                   else {
                       return "This item is not capable of being equipped.\n";
                   }
               }
           }
           return "No such item in inventory.\n";
       }
    }

    public String unEquipItem(String itemName) {
        itemName = itemName.stripTrailing();
        if (weapon.get_itemName().equalsIgnoreCase(itemName) && !weapon.get_itemName()
                .equalsIgnoreCase("hands")) {
            // remove attack points
            setDamage(damage - weapon.get_damageValue());
            // set unequipped
            weapon.setEquipped(false);
            // equip bare hands
            setWeapon(new Item(0, "Hands", "Your Hands",
                    "None", 0, 0, "Weapon", 0,
                    0.0f, false, false));
            return "Player unequipped " + itemName + "\n" +
                    "Attack points equal: " + damage + "\n";
        }
        else if (wearable.get_itemName().equalsIgnoreCase(itemName)) {
            // remove health
            setMaxHitPoints(getMaxHitPoints() - weapon.get_totalHpModifier());
            if (currentHitPoints > maxHitPoints) {
                currentHitPoints = maxHitPoints;
            }
            // add damage back
            setDamage(getDamage() + wearable.get_damageValue());
            // equip item
            wearable.setEquipped(false);
        }
        if (itemName.equalsIgnoreCase("Hands")) {
            return "You can't remove your hands!\n";
        }
        if (!weapon.get_itemName().equalsIgnoreCase(itemName)) {
            return "This item is not equipped.";
        }
        // if this item is not equipped you can't unequip
        return "No such item in inventory.\n";
    }
    /**
     * Help method that will display a
     * list of commands the player can use.
     * @author David Huber and Khamilah Nixon
     * @return a list of commands
     */
    // Author: Khamilah and David
    public String help() {
        StringBuilder commandList = new StringBuilder();
        BufferedReader file = null;
        try {
            file = new BufferedReader(new FileReader("src/Character/CharacterTextFiles/CommandList.txt"));
            while(file.ready()) {
                String next = file.readLine();
                commandList.append(next).append("\n");
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return commandList.toString().stripTrailing();
    }

    /**
     * Author: Brian Smithers
     */
        public boolean traverseRooms(String direction) {
        // TODO fix issue with currentRoom
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
                "block chance: " + blockChance + "\n" +
                "equipped weapon: " + weapon.get_itemName() + "\n";
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

    public InventoryController getInventoryController() {
        return inventoryController;
    }

    public Item getWeapon() {
        return weapon;
    }

    public void setWeapon(Item weapon) {
        if (weapon.get_itemType().equalsIgnoreCase("weapon")) {
            this.weapon = weapon;
        }
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