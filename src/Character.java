import java.util.LinkedList;

public class Character {

    private final int id;
    private final String name;
    private final LinkedList<Item> playerItemInventory;
    private final String description;
    private int hitPoints;
    private double dodgeChance;
    private int damage;

    public Character(int id, String name, LinkedList<Item> playerItemInventory,
                     String description, int hitPoints, double dodgeChance, int damage) {
        this.id = id;
        this.name = name;
        this.playerItemInventory = playerItemInventory;
        this.description = description;
        this.hitPoints = hitPoints;
        this.dodgeChance = dodgeChance;
        this.damage = damage;
    }

    //TODO Start Game Feature

    //TODO Save Game Feature

    // TODO Basement Key Feature

    // TODO Character select

    // TODO Help Command

    // TODO Puzzle Interact

    // TODO Inventory Command

    // TODO N, S, E, W Command for changing rooms

    // TODO Equip Item

    // TODO Regenerate Health

    // TODO Unequip Item

    // TODO Use Item

    // TODO Pick Up Item

    // TODO Item Description
}
