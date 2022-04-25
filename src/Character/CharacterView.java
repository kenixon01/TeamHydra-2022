package Character;

/**
 * Author: Brian Smithers
 */
public class CharacterView {

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
    public void printPlayerLocation(String location) {
        System.out.println("Current room: " + location);
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

    public void characterConfirm(Character character) {
        System.out.println("Would you like to continue as " + character.getName() + "? Yes or No");
    }

    /**
     * Author: Khamilah Nixon
     */
    public void printInventory(Character character) {
        System.out.println(character.getName() + "' Inventory:");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-");
        if(character.getPlayerItemInventory() != null || character.getPlayerItemInventory().size() == 0) {
            for(int i = 0; i < character.getPlayerItemInventory().size(); i++) {
                System.out.println(character.getPlayerItemInventory().get(i).get_itemName());
            }
        }
        else {
            System.out.println("Nothing in inventory");
        }
    }
}
