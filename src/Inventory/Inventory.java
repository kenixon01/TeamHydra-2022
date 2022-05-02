package Inventory;

import Item.Item;
import Puzzle.Puzzle;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Author: Brian Smithers
 */
public class Inventory implements Serializable {
    private final LinkedList<Item> itemInventory = new LinkedList<>();

    // Add object to item inventory for initialization
    public void addItem(Item item) {
        itemInventory.add(item);
    }

    // Transfer between Player, Monster or Room
    public boolean transferItem(LinkedList<Item> inventory2, String item) {
        for (int i = 0; i < inventory2.size(); i++) {
            if (inventory2.get(i).get_itemName().equalsIgnoreCase(item) &&
                    inventory2.get(i).getItemUnlocked()) {
                // Transfer item from inventory1 to inventory2
                Item tempItem = inventory2.get(i);
                itemInventory.add(tempItem);
                // Remove item from
                inventory2.remove(i);
                return true;
            }
        }
        return false;
    }

    public LinkedList<Item> getItemInventory() {
        return itemInventory;
    }
}
