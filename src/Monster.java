import java.util.HashMap;

/**
 * The Monster class defines the functionality of a monster.  It will reference the
 * {@link MonsterReader} class to define the following attributes for a monster:
 * <blockquote>
 *     <ul>
 *         <li>ID - Monster's unique identifier</li>
 *         <li>ROOM ID - Unique identification of the room where the monster lies</li>
 *         <li>ITEM ID - Unique identification of the item that the monster drops upon death</li>
 *         <li>HP - Monster's initial health point</li>
 *         <li>DAMAGE - Monster's attack damage</li>
 *         <li>NAME - Monster's name</li>
 *         <li>DESCRIPTION - Information about the monster</li>
 *     </ul>
 * </blockquote>
 *
 * @since 1.0
 * @author Khamilah E. Nixon
 * @version 1.0
 */
public class Monster {
    private final int ID, ROOM_ID, ITEM_ID, HP, DAMAGE;

    private final String NAME, DESCRIPTION;

    private HashMap<Integer,Item> inventory = new HashMap<>();

    /**
     * Creates a monster object and assigns values to fields using
     * a {@link MonsterReader} reference
     */
    public Monster() {
        MonsterReader reader = new MonsterReader();
        ID = reader.getID();
        ROOM_ID = reader.getROOM_ID();
        ITEM_ID = reader.getITEM_ID();
        HP = reader.getHP();
        DAMAGE = reader.getDAMAGE();
        NAME = reader.getNAME();
        DESCRIPTION = reader.getDESCRIPTION();
    }

    public int getID() {
        return ID;
    }

    public int getROOM_ID() {
        return ROOM_ID;
    }

    public int getITEM_ID() {
        return ITEM_ID;
    }

    public int getHP() {
        return HP;
    }

    public int getDAMAGE() {
        return DAMAGE;
    }

    public String getNAME() {
        return NAME;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public HashMap<Integer, Item> getInventory() {
        return inventory;
    }
}
