package Character;

/**
 * Author: Brian Smithers
 */
public class CharacterView {

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
}
