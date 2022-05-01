package Character;

import Inventory.*;
import Item.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;

/*** Author: Brian Smithers */
public class CharacterReader implements Serializable {

    private File file;
    private Scanner scanner;

    private final String characterFilePath;
    private final String startingItemFilePath;
    private final String characterDescriptionFilePath;
    private final String starterItemDescriptionFilePath;

    private String charId;
    private String charName;
    private String charDescription;
    private int hitPoints;
    private double dodgeChance;
    private int damage;

    private int itemId;
    private String itemName;
    private String itemType;
    private String itemDescription;
    private String itemRoomId;
    private int itemDamage;
    private int itemHealthRestoration;
    private int itemTotalHitPointsModifier;

    private Character character;

    public CharacterReader(String characterFilePath, String startingItemFilePath,
                           String characterDescriptionFilePath,
                           String starterItemDescriptionFilePath) {
        this.characterFilePath = characterFilePath;
        this.startingItemFilePath = startingItemFilePath;
        this.characterDescriptionFilePath = characterDescriptionFilePath;
        this.starterItemDescriptionFilePath = starterItemDescriptionFilePath;
    }

    public void read() {
        readChar();
        readCharDescription();
        readCharStarterItem();
        readCharStarterItemDescription();
        createCharacter();
    }

    public void createCharacter() {
        character = new Character(charId, charName,
                new InventoryController(new Inventory(), new InventoryView()),
                charDescription, hitPoints, dodgeChance, damage);
        addStarterItemToPlayerInventoryAndEquipItem();
    }

    private Item createStarterItem() {
        return new Item(itemId, itemName, itemDescription,
                itemRoomId, itemDamage, itemHealthRestoration, itemType,
                itemTotalHitPointsModifier, 0.0f, false,
                false, true);
    }

    private void addStarterItemToPlayerInventoryAndEquipItem() {
        Item starterItem = createStarterItem();
        character.getInventoryController().getItemInventory().add(starterItem);
        character.setMaxHitPoints(
                character.getMaxHitPoints() + starterItem.get_totalHpModifier());
        character.setCurrentHitPoints(character.getMaxHitPoints());
        character.setDamage(starterItem.get_damageValue());
        character.setWeapon(starterItem);
        starterItem.setEquipped(true);
    }

    public Character getCharacter() {
        return character;
    }

    // Read in character's id, name, hp and dodge chance
    public void readChar() {
        try {
            File file = new File(characterFilePath);
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                if (scanner.nextLine().equalsIgnoreCase("character id")) {
                    charId = scanner.nextLine();
                }
                if (scanner.nextLine().equalsIgnoreCase("name")) {
                    charName = scanner.nextLine();
                }
                if (scanner.nextLine().equalsIgnoreCase("hp")) {
                    hitPoints = Integer.parseInt(scanner.nextLine());
                }
                if (scanner.nextLine().equalsIgnoreCase("dodge chance")) {
                    dodgeChance = Double.parseDouble(scanner.nextLine());
                }
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println(getClass() + " " + ex + " while reading " +
                    " characterFilePath.txt");
        }
    }

    // Read in character's description
    public void readCharDescription() {
        try {
            file = new File(characterDescriptionFilePath);
            scanner = new Scanner(file);
            charDescription = "";

            while (scanner.hasNext()) {
                charDescription += scanner.nextLine() + "\n";
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println(getClass() + " " + ex + " while reading " +
                    " characterDescriptionFilePath.txt");
        }
    }

    // Read in character's starter item
    public void readCharStarterItem() {
        try {
            file = new File(startingItemFilePath);
            scanner = new Scanner(file);

            while (scanner.hasNext()) {
                if (scanner.nextLine().equalsIgnoreCase("item id")) {
                     itemId = Integer.parseInt(scanner.nextLine());
                }
                if (scanner.nextLine().equalsIgnoreCase("item name")) {
                     itemName = scanner.nextLine();
                }
                if (scanner.nextLine().equalsIgnoreCase("item type")) {
                     itemType = scanner.nextLine();
                }
                if (scanner.nextLine().equalsIgnoreCase("room the item is located in")) {
                     itemRoomId = scanner.nextLine();
                }
                if (scanner.nextLine().equalsIgnoreCase("damage value")) {
                     itemDamage = Integer.parseInt(scanner.nextLine());
                }
                if (scanner.nextLine().equalsIgnoreCase("health restoration value")) {
                     itemHealthRestoration = Integer.parseInt(scanner.nextLine());
                }
                if (scanner.nextLine().equalsIgnoreCase("total hp modifier")) {
                     itemTotalHitPointsModifier = Integer.parseInt(scanner.nextLine());;
                }
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println(getClass() + " " + ex + " while reading " +
                    " startingItemFilePath.txt");
        }
    }

    public void readCharStarterItemDescription() {
        try {
            file = new File(starterItemDescriptionFilePath);
            scanner = new Scanner(file);

            while (scanner.hasNext()) {
                itemDescription += scanner.nextLine() + "\n";
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println(getClass() + " " + ex + " while reading " +
                    " starterItemDescriptionFilePath.txt");
        }
    }
}
