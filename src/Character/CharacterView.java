package Character;


import java.io.Serializable;
import Utilities.ConsoleColors;

public class CharacterView implements Serializable {
    private ConsoleColors colors = new ConsoleColors();

    public CharacterView() {
        colors.setTextColor("cyan");
    }

    /**
     * Author: Brian Smithers
     */
    public void failedRoomTraversal(boolean traverse) {
        if (!traverse) {
            System.out.println(colors.textColor("You can't travel here...","b/red"));
        }
    }

    /**
     * Author: Brian Smithers
     */
    public void printHelp(String commandList) {
        System.out.println(
                colors.textColor(commandList,"b/purple"));
    }

    /**
     * Author: Brian Smithers
     */
    public void printPlayerDetails(String playerDetails) {
        System.out.println(
                colors.textColor(playerDetails));
    }

    /**
     * Author: Brian Smithers
     */
    public void printPlayerLocation(int roomNumber, String roomName) {
        System.out.println(colors.textColor("Room Number: ") +
                colors.textColor(roomNumber + "\n", "yellow") +
                colors.textColor("Room Name: ") +
                colors.textColor(roomName, "yellow"));
    }

    /**
     * Author: Khamilah Nixon
     */
    public void characterSelect() {
        System.out.println(
                colors.textColor("Choose your character:","yellow"));
        System.out.println(
                colors.textColor("1.\t","yellow") +
                      "Leuthere, the Vampire Slayer");
        System.out.println(
                colors.textColor("2.\t","yellow") +
                        "Aldred Kiyotosuna");
        System.out.println(
                colors.textColor("3.\t","yellow") +
                      "Artemis of the Broken Heart");
        System.out.println(
                colors.textColor("4.\t","yellow") +
                        "Locke Zsahbdiin");
    }

    /**
     * Author: Khamilah Nixon
     */
    public void printInventory(Character character) {
        System.out.println(
                colors.textColor(character.getName() + "' Inventory:"));
        System.out.println(
                colors.textColor("-*-*-*-*-*-*-*-*-*-*-*-*-", "yellow"));
        if(character.getInventoryController().getItemInventory() != null ||
                character.getInventoryController().getItemInventory().size() == 0) {
            for(int i = 0; i < character.getInventoryController().getItemInventory().size(); i++) {
                // i + 1 represents the item's number in the slot
                // There is a zero slot but it will be referred to as 1 in game.
                System.out.println(
                        colors.textColor(
                                i + 1 + " - " + character.getInventoryController().
                        getItemInventory().get(i).get_itemName(), "yellow"));
            }
        }
        else {
            System.out.println(colors.textColor("Nothing in inventory","yellow"));
        }
    }
}
