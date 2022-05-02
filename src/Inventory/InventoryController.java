package Inventory;

import Item.Item;
import Character.Character;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Author: Brian Smithers
 */
public class InventoryController implements Serializable {
    private Inventory model;
    private InventoryView view;

    public InventoryController(Inventory model, InventoryView view) {
        this.model = model;
        this.view = view;
    }

    public void transferItem(String roomName, String action, LinkedList<Item> inventory2,
                             String item, Character character) {
        model.transferItem(character, inventory2, item);
    }

    public Inventory getModel() {
        return model;
    }

    public LinkedList<Item> getItemInventory() {
        return model.getItemInventory();
    }

    public void addItem(Item item) {
        model.addItem(item);
    }
}
