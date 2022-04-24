package Item;

import Character.Character;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *  Author: Jayson Dasher
 */
public class ItemController {
    private Map<String, List<Item>> model;
    private ItemView view;

    public ItemController(Map<String, List<Item>> model, ItemView view) {
        this.model = model;
        this.view = view;
    }

    //TODO: EQUIP ITEM
    public void equipItem(){
        view.printinventory(model.get("0"));
        //get input
        //if item is weapon, check if there is an equipped item and reduce damage modifier accordingly, set isEquipped to false
            //use inputted item damage value to alter character damage value and set isEquipped to true
        //if item is wearable, add total hp modifier to characters total allowed hp, add hp to actual hp value
        //else , that item is not equippable.



        //

    }

    //TODO: UNEQUIP ITEM
    public void unequipItem(){
        //check if any items in inventory are currently equipped
        //if not, print out message
        //if so, print list of items marked equipped.
        //get input
        //if input is equipped item, mark item unequipped and alter character values accordingly.
    }

    //TODO: USE ITEM
    public void useItem(Character character){
        inventory();
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        //find item
        Item currentItem = model.get("0").get(Integer.parseInt(input)-1);
        //check to see if consumable
        if (currentItem.get_itemType().equalsIgnoreCase("Consumable")) {
            //alter health
            character.setHp(character.getHp() + currentItem.get_healValue());
            // delete item from local inventory and character inventory
            model.get("0").remove(Integer.parseInt(input)-1);
            character.getPlayerItemInventory().remove(Integer.parseInt(input)-1);
            //TODO: alter character damage based on item damage value modifier
        }
        else {
             view.notEquipable(currentItem);
        }
    }

    public void inventory(){
        view.printinventory(model.get("0"));
    }

    //if calling function, required inputs are roomnumber and character.
    public void pickupItem(String roomNum, Character character){
        Item currentItem = model.get(roomNum).get(0);
        view.itemPromt(currentItem);
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        if (input.equalsIgnoreCase("yes")){
            view.printItemDescription(currentItem);
            currentItem = model.remove(roomNum).get(0);
            List<Item> playerInventory = model.get("0");
            playerInventory.add(currentItem);
            model.put("0", playerInventory);
            character.getPlayerItemInventory().add(currentItem);
        }
        else {
            view.itemIgnored(currentItem);
        }
    }


}
