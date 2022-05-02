package Item;

import Character.Character;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Author: Jayson Dasher
 */
public class ItemController implements Serializable {
    // Room Number, Item for the Map
    private Map<String, List<Item>> model;
    private ItemView view;

    public ItemController(Map<String, List<Item>> model, ItemView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Author: Jayson Dasher and Khamilah Nixon
     */
    public void useItem(Character character, String input) {
        Item currentItem = null;
        boolean validCommand = false;
        try {
            int inventoryNumber = Integer.parseInt(input.trim());
            currentItem = character.getInventoryController().getItemInventory().get(inventoryNumber - 1);
            validCommand = true;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            view.invalidInventoryChoice();
        }

        //check to see if consumable
        if(validCommand) {
            if (currentItem.get_itemType().equalsIgnoreCase("Consumable")) {
                //alter health
                character.setCurrentHitPoints(character.getCurrentHitPoints() + currentItem.get_healValue());
                if (character.getCurrentHitPoints() > character.getMaxHitPoints()) {
                    character.setCurrentHitPoints(character.getMaxHitPoints());
                }
                //alter character damage output
                character.setDamage(character.getDamage() + currentItem.get_damageValue());
                // delete item from local inventory and character inventory
                character.getInventoryController().getItemInventory().remove(Integer.parseInt(input.trim()) - 1);
                //character.getPlayerItemInventory().remove(Integer.parseInt(input)-1);
            } else {
                view.notEquipable(currentItem);
            }
        }
    }
}
