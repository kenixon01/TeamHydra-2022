package Item;

import Character.Character;

import java.io.Serializable;
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

    // EQUIP ITEM
    public void equipItem(Character character) {
        //print inventory
        view.printinventory(character.getInventoryController().getItemInventory());
        //get input
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        Item currentItem = character.getInventoryController().getItemInventory().get(Integer.parseInt(input) - 1);
        //if item is weapon, check if there is an equipped item and reduce damage modifier accordingly, set isEquipped to false
        //use inputted item damage value to alter character damage value and set isEquipped to true
        if (currentItem.get_itemType().equalsIgnoreCase("weapon")) {
            //find if another weapon is equipped. unequip it and deduct damage value from character
            for (int i = 0; i < character.getInventoryController().getItemInventory().size(); i++) {
                if ((character.getInventoryController().getItemInventory().get(i).get_itemType().equalsIgnoreCase("weapon")
                        && character.getInventoryController().getItemInventory().get(i).getEquipped())) {
                    //set boolean for original equipped item to false (unequipping it)
                    character.getInventoryController().getItemInventory().get(i).setEquipped(false);
                    //reduce character damage by weapons damage modifier
                    character.setDamage(character.getDamage() - character.getInventoryController().getItemInventory().get(i).get_damageValue());
                    //set input selection eqipped value to true
                    currentItem.setEquipped(true);
                    //add weapon damage modifier to character damage
                    character.setDamage(character.getDamage() + currentItem.get_damageValue());
                } else {
                    currentItem.setEquipped(true);
                    character.setDamage(character.getDamage() + currentItem.get_damageValue());
                }
            }
        }
        //if item selected is wearable
        else if (currentItem.get_itemType().equalsIgnoreCase("wearable")) {
            //set equipped to true
            currentItem.setEquipped(true);
            // set player max health value to current value plus modifier
            character.setMaxHitPoints(character.getMaxHitPoints() + currentItem.get_totalHpModifier());
            // set player health to current health plus modifier
            character.setCurrentHitPoints(character.getCurrentHitPoints() + currentItem.get_healValue());
        }
        //input is an item that is not equippable
        else {
            view.notEquipable(currentItem);
        }
    }

    public void unequipItem(Character character) {
        int equippedCount = 0;
        //check if any items in inventory are currently equipped
        for (int i = 0; i < character.getInventoryController().getItemInventory().size(); i++) {
            if (character.getInventoryController().getItemInventory().get(i).getEquipped()) {
                equippedCount += 1;
                System.out.println(Integer.toString(equippedCount - 1) + ". " + character.getInventoryController().getItemInventory().get(i).get_itemName());
//                view.printNumName(character.getInventoryController().getItemInventory().get(i));
            }
        }
        //if not, print out message
        if (equippedCount == 0) {
            view.noneEquipped();
        } else {
            //get input
            Scanner reader = new Scanner(System.in);
            String input = reader.nextLine();
            Item currentItem = character.getInventoryController().getItemInventory().get(Integer.parseInt(input));
            //if input is equipped item, mark item unequipped and alter character values accordingly.
            if (currentItem.getEquipped() == true) {
                currentItem.setEquipped(false);
                character.setDamage(character.getDamage() - currentItem.get_damageValue());
                character.setMaxHitPoints(character.getMaxHitPoints() + currentItem.get_healValue());
            }
        }


    }

    public void useItem(Character character) {
        //print inventory
        view.printinventory(character.getInventoryController().getItemInventory());
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        //find item
        Item currentItem = character.getInventoryController().getItemInventory().get(Integer.parseInt(input) - 1);
        //check to see if consumable
        if (currentItem.get_itemType().equalsIgnoreCase("Consumable")) {
            //alter health
            character.setCurrentHitPoints(character.getCurrentHitPoints() + currentItem.get_healValue());
            if (character.getCurrentHitPoints() > character.getMaxHitPoints()) {
                character.setCurrentHitPoints(character.getMaxHitPoints());
            }
            //alter character damage output
            character.setDamage(character.getDamage() + currentItem.get_damageValue());
            // delete item from local inventory and character inventory
            character.getInventoryController().getItemInventory().remove(Integer.parseInt(input) - 1);
            //character.getPlayerItemInventory().remove(Integer.parseInt(input)-1);
        } else {
            view.notEquipable(currentItem);
        }
    }


    //if calling function, required inputs are roomnumber and character.
    public void pickupItem(String roomNum, Character character) {
        Item currentItem = model.get(roomNum).get(0);
        view.itemPromt(currentItem);
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        if (input.equalsIgnoreCase("yes")) {
            view.printItemDescription(currentItem);
            List<Item> playerInventory = model.get("0");
            currentItem = model.remove(roomNum).get(0);
            playerInventory.add(currentItem);
            model.put("0", playerInventory);
            //character.getPlayerItemInventory().add(currentItem);
        } else {
            view.itemIgnored(currentItem);
        }
    }

    public void addToInventory(String roomNum, Character character) {
        List<Item> playerInventory = model.get("0");
        Item currentItem = model.remove(roomNum).get(0);
        playerInventory.add(currentItem);
        model.put("0", playerInventory);
        //character.getPlayerItemInventory().add(currentItem);
    }

    //main controller calls upon this to check for any items in the room
    public void checkRoomItem(String roomNum, Character character) {
        if (model.get(roomNum).size() > 0) {
            for (int i = 0; i < model.get(roomNum).size(); i++) {
                pickupItem(roomNum, character);
            }
        }
    }

//    public void getPlayerStarterItems(Character character){
//        model.get("0").add(character.getPlayerItemInventory().get(0));
//    }

    public Map<String, List<Item>> getModel() {
        return model;
    }
}
