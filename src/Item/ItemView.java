package Item;

import java.io.Serializable;
import java.util.List;

/**
 * Author: Jayson Dasher
 */
public class ItemView implements Serializable {

    public void invalidInventoryChoice() {
        System.out.println("Enter a valid number associated with an item in your inventory");
    }

    public void notEquipable(Item item) {
        System.out.println(item.get_itemName() + " is not equipable.");
    }
}
