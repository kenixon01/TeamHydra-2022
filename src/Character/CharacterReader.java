package Character;

import Item.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/*** Author: Brian Smithers */
public class CharacterReader {

    private File file;
    private Scanner scanner;

    private String characterFilePath;
    private String startingItemFilePath;
    private String characterDescriptionFilePath;

    private String id;
    private String name;
    private LinkedList<Item> playerItemInventory;
    private String description;
    private int hitPoints;
    private double dodgeChance;
    private int damage;

    private Character character;

    public CharacterReader(String characterFilePath, String startingItemFilePath,
                           String characterDescriptionFilePath) {
        this.characterFilePath = characterFilePath;
        this.startingItemFilePath = startingItemFilePath;
        this.characterDescriptionFilePath = characterDescriptionFilePath;
    }

    public void read() {
        // Read in character's id, name, hp and dodge chance
        // Read in character's starter item
        // Read in character's description
        readChar();
        readCharDescription();
        character = new Character(id, name, new LinkedList<>(), description,
                hitPoints, dodgeChance, damage);
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
                    id = scanner.nextLine();
                }
                if (scanner.nextLine().equalsIgnoreCase("name")) {
                    name = scanner.nextLine();
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
            description = "";

            while (scanner.hasNext()) {
                description += scanner.nextLine() + "\n";
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println(getClass() + " " + ex + " while reading " +
                    " characterDescriptionFilePath.txt");
        }
    }

    // TODO implement the readCharStarterItem function
    // Read in character's starter item
    public void readCharStarterItem() {
        try {
            file = new File(startingItemFilePath);
            scanner = new Scanner(file);

            while (scanner.hasNext()) {

            }
        } catch (FileNotFoundException ex) {
            System.out.println(getClass() + " " + ex + " while reading " +
                    " startingItemFilePath.txt");
        }
    }
}
