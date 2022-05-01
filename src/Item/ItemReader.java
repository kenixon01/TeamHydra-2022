package Item;

/**
 *  Author: Jayson Dasher
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class  ItemReader implements Serializable {

    private String _itemsFilePath;

    public ItemReader() { this._itemsFilePath = (System.getProperty("user.dir") + "\\src\\Item\\"); }

    // Purpose: to only attempt to reach the file and create it.
    // Read the user's input of "gameRooms.txt"
    // See if "gameRooms.txt" EXISTS, THEN if it DOES exist, return the File so that
    // it can be read line by line
    // from WITHIN GameConstructor.ConstructGame()

    private Scanner GetFileInputString() {
        Scanner reader;

        while (true) {
            File newFile = new File(_itemsFilePath + "items.txt");

            try {
                reader = new Scanner(newFile);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found. Re-HARD CODE your file name.");
            }
        }

        return reader;
    }

    /**
     * Author: Jayson and Brian
     */
    public Map<String, List<Item>> CreateItems() {
        Scanner reader = this.GetFileInputString();

        Map<String, List<Item>> items = new HashMap<>();
        Item currItem; // dummy

        while (reader.hasNextLine()) { // reading through file loop
            String currLine = reader.nextLine();
            if (currLine.length() > 0 && currLine.charAt(0) == '-') {
                continue;
            }

            if (currLine.equals("")) { // New item
                int itemNum = reader.nextInt();
                reader.nextLine();
                String itemName = reader.nextLine();
                String itemType = reader.nextLine();
                String itemDesc = reader.nextLine();
                String itemRoom = reader.nextLine();
                int damageValue = reader.nextInt();
                int healthValue = reader.nextInt();
                reader.nextLine();
                int hpModifier = reader.nextInt();
                float critValue = reader.nextFloat(); // not implemented
                currItem = new Item(itemNum, itemName, itemDesc, itemRoom, damageValue, healthValue, itemType,
                        hpModifier, critValue, false,false);

                ArrayList<Item> listOfItems = new ArrayList<>();
                listOfItems.add(currItem);
                items.put(currItem.get_itemRoom(), listOfItems);
            }
        }
        return items;
    }
}

class ItemReaderTester {
    public static void main(String[] args) {
        ItemReader itemReader = new ItemReader();
        itemReader.CreateItems();
    }
}