package Character;


import java.io.Serializable;

public class CharacterView implements Serializable {

    /**
     * Author: Brian Smithers
     */
    public void failedRoomTraversal(boolean traverse) {
        if (!traverse) {
            System.out.println("You can't travel here...");
        }
    }

    /**
     * Author: Brian Smithers
     */
    public void printHelp(String commandList) {
        System.out.println(commandList);
    }

    /**
     * Author: Brian Smithers
     */
    public void printPlayerDetails(String playerDetails) {
        System.out.println(playerDetails);
    }

    /**
     * Author: Brian Smithers
     */
    public void printPlayerLocation(int roomNumber, String roomName) {
        System.out.println("Room Number: " + roomNumber
                + "\n" + "Room Name: " + roomName);
    }

    /**
     * Author: Khamilah Nixon
     */
    public void characterSelect() {
        System.out.println("Choose your character:");
        System.out.println("1.\tLeuthere, the Vampire Slayer");
        System.out.println("2.\tAldred Kiyotosuna");
        System.out.println("3.\tArtemis of the Broken Heart");
        System.out.println("4.\tLocke Zsahbdiin");
    }

    /**
     * Author: Khamilah Nixon
     */
    public void printInventory(Character character) {
        System.out.println(character.getName() + "' Inventory:");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-");
        if(character.getInventoryController().getItemInventory() != null ||
                character.getInventoryController().getItemInventory().size() == 0) {
            for(int i = 0; i < character.getInventoryController().getItemInventory().size(); i++) {
                // i + 1 represents the item's number in the slot
                // There is a zero slot but it will be referred to as 1 in game.
                System.out.println(i + 1 + " - " + character.getInventoryController().
                        getItemInventory().get(i).get_itemName());
            }
        }
        else {
            System.out.println("Nothing in inventory");
        }
    }
}
