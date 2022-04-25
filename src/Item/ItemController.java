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

    // EQUIP ITEM
    public void equipItem(Character character){
        //print inventory
        view.printinventory(model.get("0"));
        //get input
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        Item currentItem = model.get("0").get(Integer.parseInt(input)-1);
        //if item is weapon, check if there is an equipped item and reduce damage modifier accordingly, set isEquipped to false
            //use inputted item damage value to alter character damage value and set isEquipped to true
        if(currentItem.get_itemType().equalsIgnoreCase("weapon")){
            //find if another weapon is equipped. unequip it and deduct damage value from character
            for (int i = 0; i < model.get("0").size(); i++) {
                if ((model.get("0").get(i).get_itemType().equalsIgnoreCase("weapon")
                        && model.get("0").get(i).getEquipped())){
                    //set boolean for original equipped item to false (unequipping it)
                    model.get("0").get(i).setEquipped(false);
                    //reduce character damage by weapons damage modifier
                    character.setDamage(character.getDamage() - model.get("0").get(i).get_damageValue());
                    //set input selection eqipped value to true
                    currentItem.setEquipped(true);
                    //add weapon damage modifier to character damage
                    character.setDamage(character.getDamage() + currentItem.get_damageValue());
                }
            }
        }
        //if item selected is wearable
        else if(currentItem.get_itemType().equalsIgnoreCase("wearable")){
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

    public void unequipItem(Character character){
        int equippedCount = 0;
        //check if any items in inventory are currently equipped
        for (int i = 0; i < model.get("0").size(); i++) {
            if (model.get("0").get(i).getEquipped() == true) {
                equippedCount += 1;
                view.printNumName(model.get("0").get(i));
            }
        }
            //if not, print out message
            if (equippedCount == 0) {
                view.noneEquipped();
            }
            else {
                //get input
                Scanner reader = new Scanner(System.in);
                String input = reader.nextLine();
                Item currentItem = model.get("0").get(Integer.parseInt(input));
                //if input is equipped item, mark item unequipped and alter character values accordingly.
                if (currentItem.getEquipped() == true){
                    currentItem.setEquipped(false);
                    character.setDamage(character.getDamage() - currentItem.get_damageValue());
                    character.setHp(character.getHp() + currentItem.get_healValue());
                }
            }



    }

    public void useItem(Character character) {
        view.printinventory(model.get("0"));
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        //find item
        Item currentItem = model.get("0").get(Integer.parseInt(input)-1);
        //check to see if consumable
        if (currentItem.get_itemType().equalsIgnoreCase("Consumable")) {
            //alter health
            character.setHp(character.getHp() + currentItem.get_healValue());
            //alter character damage output
            character.setDamage(character.getDamage() + currentItem.get_damageValue());
            // delete item from local inventory and character inventory
            model.get("0").remove(Integer.parseInt(input)-1);
            //character.getPlayerItemInventory().remove(Integer.parseInt(input)-1);
        }
        else {
             view.notEquipable(currentItem);
        }
    }


    //if calling function, required inputs are roomnumber and character.
    public void pickupItem(String roomNum, Character character){
        Item currentItem = model.get(roomNum).get(0);
        view.itemPromt(currentItem);
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        if (input.equalsIgnoreCase("yes")){
            view.printItemDescription(currentItem);
            List<Item> playerInventory = model.get("0");
            currentItem = model.remove(roomNum).get(0);
            playerInventory.add(currentItem);
            model.put("0", playerInventory);
            //character.getPlayerItemInventory().add(currentItem);
        }
        else {
            view.itemIgnored(currentItem);
        }
    }

    public void addToInventory(String roomNum, Character character){
        List<Item> playerInventory = model.get("0");
        Item currentItem = model.remove(roomNum).get(0);
        playerInventory.add(currentItem);
        model.put("0", playerInventory);
        //character.getPlayerItemInventory().add(currentItem);
    }
    //main controller calls upon this to check for any items in the room
    public void checkRoomItem(String roomNum, Character character){
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
