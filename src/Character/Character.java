package Character;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Inventory.*;
import Item.Item;
import Room.Room;

import java.io.Serializable;
import java.util.*;

/**
 * Author: Brian Smithers
 * Co Authors: Khamilah
 */

public class Character implements Serializable {
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
    private Item wearable; // unused

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
                        characterDescriptionFilePath,
                        startingItemDescriptionFilePath);
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
                        characterDescriptionFilePath,
                        startingItemDescriptionFilePath);
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
                        characterDescriptionFilePath,
                        startingItemDescriptionFilePath);
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
                        characterDescriptionFilePath,
                        startingItemDescriptionFilePath);
                cr.read();
            }
            default -> { // No default.
            }
        }
        assert cr != null;
        return cr.getCharacter();
    }

    public boolean equipItem(String itemSlot) {
        itemSlot = itemSlot.stripTrailing();
        Item item = inventoryController.getItemInventory().get(Integer.parseInt(itemSlot) - 1);
        if (item.get_itemType().equalsIgnoreCase("weapon")) {
            // Add weapon to users weapon slot
            setWeapon(item);
            setDamage(item.get_damageValue());
            // equip item
            item.setEquipped(true);
        }
        else if (item.get_itemType().equalsIgnoreCase("wearable")) {
            // Increase max health if the item increases total hp
            setMaxHitPoints(getMaxHitPoints() + item.get_totalHpModifier());
            // If the item restores health on pick up, restore health.
            if (item.isRestoreHealthOnPickUp()) {
                item.setRestoreHealthOnPickUp(false); // item can't restore health again
                setCurrentHitPoints(getMaxHitPoints());
            }
            // Increase damage
            if (item.get_damageValue() > 0) {
                setDamage(getDamage() + item.get_damageValue());
            }
            setWearable(item);
            wearable.setEquipped(true);
            item.setEquipped(true);
        }
        return true;
    }

    public boolean unEquipItem(String itemSlot) {
        itemSlot = itemSlot.stripTrailing();
        Item item = inventoryController.getItemInventory().get(Integer.parseInt(itemSlot) - 1);
        String itemName = item.get_itemName();
        if (weapon.get_itemName().equalsIgnoreCase(itemName) && !weapon.get_itemName()
                .equalsIgnoreCase("hands")) {
            // remove attack points
            setDamage(damage - weapon.get_damageValue());
            // set unequipped
            weapon.setEquipped(false);
            // equip bare hands
            setWeapon(new Item(0, "Hands", "Your Hands",
                    "None", 0, 0, "Weapon", 0,
                    0.0f, false, false, true));
        }
        if (wearable != null) {
            if (wearable.get_itemName().equalsIgnoreCase(itemName)) {
                // remove health
                setMaxHitPoints(getMaxHitPoints() - item.get_totalHpModifier());
                if (currentHitPoints > maxHitPoints) {
                    currentHitPoints = maxHitPoints;
                }
                // add damage back
                setDamage(getDamage() + wearable.get_damageValue());
                // unequip item
                setWearable(new Item(0, "Empty", "None",
                        "None", 0, 0, "wearable", 0,
                        0, true, false, true));
                wearable.setEquipped(false);
            }
        }
        return false;
    }

    /**
     * Help method that will display a
     * list of commands the player can use.
     *
     * @return a list of commands
     * @author David Huber and Khamilah Nixon
     */
    // Author: Khamilah and David
    public String help() {
        StringBuilder commandList = new StringBuilder();
        BufferedReader file = null;
        try {
            file = new BufferedReader(new FileReader("src/Character/CharacterTextFiles/CommandList.txt"));
            while (file.ready()) {
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
        // Get the player's current room and its connections.
        Room currentRoom = Objects.requireNonNull(Room.getRoom(getRoomNumber()));
        String[][] roomConnections = currentRoom.getRoomConnections();

        for (String[] room : roomConnections) {
                for (int j = 0; j < room.length; j++) {
                    // Look for direction (N, S, E, W) in the array.
                    if (room[j].equalsIgnoreCase(direction)) {
                        // Retrieve the room number associated with the direction.
                        int newRoomNumber = Integer.parseInt(room[j - 1]);

                        /*
                        Check if the player's room is locked by looking at its lock status. If the room is locked
                        get the keys required to open the door. Player's inventory will be compared to the keys.
                         */
                        boolean checkIfRoomsIsLocked = Room.getRoom(newRoomNumber).isLocked();
                        if (checkIfRoomsIsLocked) {
                            String[] keysRequiredToOpenDoor = Room.getRoom(newRoomNumber).getKeysRequired();
                            int numberOfKeysRequired = keysRequiredToOpenDoor.length;
                            for (int i = 0; i < inventoryController.getItemInventory().size(); i++) {
                                for (String s : keysRequiredToOpenDoor) {
                                    if (inventoryController.getItemInventory().get(i).get_itemName()
                                            .equalsIgnoreCase(s)) {
                                        numberOfKeysRequired--;
                                    }
                                }
                            }
                            if (numberOfKeysRequired != 0) {
                                System.out.println("This room is currently locked.");
                                return false;
                            }
                            else {
                                System.out.println("The door has been unlocked.");
                                setRoomNumber(newRoomNumber);
                                return true;
                            }
                        }
                        // No keys are required for this operation.
                        setRoomNumber(newRoomNumber);
                        return true;
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

    public void setWearable(Item wearable) {
        this.wearable = wearable;
    }

    public Item getWearable() {
        return wearable;
    }
}
