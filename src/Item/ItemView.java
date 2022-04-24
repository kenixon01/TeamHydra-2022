package Item;

import java.util.List;

/**
 *  Author: Jayson Dasher
 */
public class ItemView {

   public ItemView(){}
    public void printItemName(Item item){
        System.out.println(item.get_itemName());
    }

    public void printItemDescription(Item item){
        System.out.println(item.get_itemDescription());
    }

    public void printNumName(Item item){
       System.out.println(item.get_itemNumber() + ". " + item.get_itemName());
    }

    public void itemPromt(Item item){
        System.out.println(item.get_itemName() + " is in your vicinity. Would you like to pick it up?");
    }
    public void itemIgnored(Item item){
        System.out.println("Item has been left on the ground.");
    }

    public void printinventory(List<Item> items){
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + 1 + ". " + items.get(i).get_itemName());
        }
    }
    public void notEquipable(Item item){
       System.out.println(item.get_itemName() + " is not equipable.");
    }

    public void noneEquipped(){
       System.out.println("You do not have any items to unequip.");
    }
}
