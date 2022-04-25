package Monster;

import Item.Item;

import java.util.HashMap;

/**
 * The Monster.Monster class defines the functionality of a monster.  It will reference the
 * {@link MonsterReader} class to define the following attributes for a monster:
 * <blockquote>
 *     <ul>
 *         <li>ID - Monster.Monster's unique identifier</li>
 *         <li>ROOM ID - Unique identification of the room where the monster lies</li>
 *         <li>ITEM ID - Unique identification of the item that the monster drops upon death</li>
 *         <li>HP - Monster.Monster's initial health point</li>
 *         <li>DAMAGE - Monster.Monster's attack damage</li>
 *         <li>NAME - Monster.Monster's name</li>
 *         <li>DESCRIPTION - Information about the monster</li>
 *     </ul>
 * </blockquote>
 *
 * @since 1.0
 * @author Khamilah E. Nixon
 * @version 1.1
 */
public class Monster {
    private final int ID, ROOM_ID, ITEM_ID, DAMAGE;
    private int Hp;

    private final String NAME, DESCRIPTION;

    private HashMap<Integer, Item> inventory = new HashMap<>();

    public static HashMap<Integer, Monster> monsterHashMap = new HashMap<>();

    /**
     * Creates a monster object and assigns values to fields using
     * a {@link MonsterReader} reference
     */
    public Monster(int ID, int ROOM_ID, int ITEM_ID, int DAMAGE, int hp, String NAME, String DESCRIPTION) {
        this.ID = ID;
        this.ROOM_ID = ROOM_ID;
        this.ITEM_ID = ITEM_ID;
        this.DAMAGE = DAMAGE;
        Hp = hp;
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;
    }

    public static HashMap<Integer,Monster>createMonsters() {
        HashMap<Integer,Monster> hashMap = new HashMap<>();
        for(int i = 0; i < 7; i++) {
            MonsterReader monsterReader = new MonsterReader();
            hashMap.put(i + 1,
                    new Monster(
                            monsterReader.getHP(),
                            monsterReader.getROOM_ID(),
                            monsterReader.getITEM_ID(),
                            monsterReader.getHP(),
                            monsterReader.getDAMAGE(),
                            monsterReader.getNAME(),
                            monsterReader.getDESCRIPTION()
                    ));
        }
        return hashMap;
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

    public int getHp() {
        return Hp;
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

    public void setHp(int hp) {
        Hp = hp;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
