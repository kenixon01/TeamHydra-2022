package Item;

/**
 *  Author: Jayson Dasher
 */
public class ItemController {

    private Item model;
    private ItemView view;

    public ItemController(Item model, ItemView view) {
        this.model = model;
        this.view = view;
    }

    public void printItemDetails() {
        //print item name
        System.out.println(model.get_itemName());
        //print item description
        System.out.println(model.get_itemDescription());
    }
}
