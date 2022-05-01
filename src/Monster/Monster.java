package Monster;

import Inventory.Inventory;
import Item.Item;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
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
 * @version 1.5
 */
public class Monster implements Serializable {
    private static final String ITEM_FILE_PATH = "src/Monster/MonsterTextFiles/Item.txt";
    private int id, roomId, itemId, damage, hp;

    private String name, description;

    private boolean isLocked;

    private Inventory inventory;

    public static HashMap<Integer, Monster> monsterHashMap = new HashMap<>();

    /**
     * Creates a monster object and assigns values to fields using
     * a {@link MonsterReader} reference
     */
    public Monster(int ID, int ROOM_ID, int ITEM_ID, int DAMAGE, int hp, String NAME, String DESCRIPTION,
                   boolean isLocked, Inventory inventory) {
        this.id = ID;
        this.roomId = ROOM_ID;
        this.itemId = ITEM_ID;
        this.damage = DAMAGE;
        this.hp = hp;
        this.name = NAME;
        this.description = DESCRIPTION;
        this.inventory = inventory;
        this.isLocked = isLocked;
    }

    /**
     * Creates a {@code HashMap<Integer,Monster>} to store all {@code Monster}
     * objects for use
     * @return list of Monsters
     * @throws IOException if line is read as incorrect type
     */
    public static HashMap<Integer,Monster>createMonsters() throws IOException {
        HashMap<Integer,Monster> hashMap = new HashMap<>();

        //loops over one of the monster files (all files have the same number of entries)
        while (MonsterReader.getNameFile().ready()){;
            MonsterReader monsterReader = new MonsterReader();

            //if a monster stores an item, add a monster with an item to the HashMap
            //otherwise, set the monster item to null
            if(monsterReader.getITEM_ID() > 0) {
                Item item = new Item(
                        monsterReader.getItemID(), monsterReader.getItemName(),
                        monsterReader.getItemDescription(), monsterReader.getItemRoomID() + "",
                        monsterReader.getItemDamage(), monsterReader.getItemHeal(),
                        monsterReader.getItemType(), monsterReader.getItemHP(),
                        monsterReader.getItemCriticalHit(), false, false);
                Inventory items = new Inventory();
                items.addItem(item);
                hashMap.put(monsterReader.getROOM_ID(), new Monster(
                        monsterReader.getID(), monsterReader.getROOM_ID(),
                        monsterReader.getITEM_ID(), monsterReader.getDAMAGE(),
                        monsterReader.getHP(), monsterReader.getNAME(),
                        monsterReader.getDESCRIPTION(), monsterReader.isLocked(),
                        items
                ));
            }
            else {
                hashMap.put(monsterReader.getROOM_ID(), new Monster(
                        monsterReader.getID(), monsterReader.getROOM_ID(),
                        monsterReader.getITEM_ID(), monsterReader.getDAMAGE(),
                        monsterReader.getHP(), monsterReader.getNAME(),
                        monsterReader.getDESCRIPTION(), monsterReader.isLocked(),null)
                );
            }
        }
        return hashMap;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public int getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setHp(int hp) {
        if (getHp() <= 0) {
            this.hp = 0;
        }
        else {
            this.hp = hp;
        }
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", itemId=" + itemId +
                ", damage=" + damage +
                ", hp=" + hp +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isLocked=" + isLocked +
                ", inventory=" + inventory +
                '}';
    }
}
