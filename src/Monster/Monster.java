package Monster;

import Item.Item;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    private static final String ITEM_FILE_PATH = "src/Monster/MonsterTextFiles/Item.txt";
    private int id, roomId, itemId, damage, hp;

    private String name, description;

    private Item item;

    public static HashMap<Integer, Monster> monsterHashMap = new HashMap<>();

    /**
     * Creates a monster object and assigns values to fields using
     * a {@link MonsterReader} reference
     */
    public Monster(int ID, int ROOM_ID, int ITEM_ID, int DAMAGE, int hp, String NAME, String DESCRIPTION, Item item) {
        this.id = ID;
        this.roomId = ROOM_ID;
        this.itemId = ITEM_ID;
        this.damage = DAMAGE;
        this.hp = hp;
        this.name = NAME;
        this.description = DESCRIPTION;
        this.item = item;
    }



    public static HashMap<Integer,Monster>createMonsters() throws FileNotFoundException {
        HashMap<Integer,Monster> hashMap = new HashMap<>();
        MonsterReader monsterReader = new MonsterReader(ITEM_FILE_PATH);
        for(int i = 0; i < 7; i++) {
            int id = monsterReader.getID();
            int roomid = monsterReader.getROOM_ID();
            int itemid = monsterReader.getITEM_ID();
            int damage = monsterReader.getDAMAGE();
            int hp = monsterReader.getHP();
            String name = monsterReader.getNAME();
            String description = monsterReader.getDESCRIPTION();
            boolean hasItem = Boolean.parseBoolean(monsterReader.getHasItem());

            int itemID = monsterReader.getItemID();
            String itemName = monsterReader.getItemName();
            String itemDescription = monsterReader.getItemDescription();
            int itemRoomID = monsterReader.getItemRoomID();
            int itemDamage = monsterReader.getItemDamage();
            int itemHeal = monsterReader.getItemHeal();
            String itemType = monsterReader.getItemType();
            int itemHP = monsterReader.getItemHP();
            int itemCriticalHit = monsterReader.getItemCriticalHit();

            if(hasItem) {
                hashMap.put(roomid, new Monster(id,roomid,itemid,damage,hp,name,description,
                        createItem(itemID, itemName, itemDescription, itemRoomID, itemDamage, itemHeal,
                        itemType, itemHP, itemCriticalHit))
                );
            }
            else {
                hashMap.put(roomid, new Monster(id,roomid,itemid,damage,hp,name,description,null));
            }
        }
        return hashMap;
    }

    private static Item createItem(int itemid, String itemName, String itemDescription, int itemRoomID, int itemDamage, int itemHeal, String itemType, int itemHP, int itemCriticalHit) {
        return new Item(
                itemid,itemName,itemDescription,itemRoomID+"",itemDamage,itemHeal,itemType,
                itemHP,itemCriticalHit,false,false);
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

    @Override
    public String toString() {
        return super.toString();
    }
}
