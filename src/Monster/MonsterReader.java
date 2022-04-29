package Monster;

import Item.Item;

import java.io.*;

/**
 * The MonsterReader class defines a monster's attributes and assigns values to those attributes
 * by reading multiple text files. All monsters are constants, and instances of this class
 * cannot alter their initial states.  All monsters have the following attributes:
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
public final class MonsterReader {
    public static BufferedReader bonkreigItem, garoldItem, highItem, robertItem, idFile, roomIDFile, itemIDFile, hpFile,
            damageFile, nameFile, descriptionFile, itemFile, lockedFile;
    static {
        try {
            bonkreigItem = new BufferedReader(new FileReader("src/Monster/MonsterTextFiles/BonkreigItem"));
            garoldItem = new BufferedReader(new FileReader("src/Monster/MonsterTextFiles/GaroldItem"));
            highItem = new BufferedReader(new FileReader("src/Monster/MonsterTextFiles/HighItem"));
            robertItem = new BufferedReader(new FileReader("src/Monster/MonsterTextFiles/RobertItem"));
            idFile = new BufferedReader(new FileReader("src/Monster/MonsterTextFiles/ID.txt"));
            roomIDFile = new BufferedReader(new FileReader("src/Monster/MonsterTextFiles/RoomID.txt"));
            itemIDFile = new BufferedReader(new FileReader("src/Monster/MonsterTextFiles/ItemID.txt"));
            hpFile = new BufferedReader(new FileReader("src/Monster/MonsterTextFiles/HP.txt"));
            damageFile = new BufferedReader(new FileReader("src/Monster/MonsterTextFiles/Damage.txt"));
            nameFile = new BufferedReader(new FileReader("src/Monster/MonsterTextFiles/Name.txt"));
            descriptionFile = new BufferedReader(new FileReader("src/Monster/MonsterTextFiles/Description.txt"));
            lockedFile = new BufferedReader(new FileReader("src/Monster/MonsterTextFiles/Locked.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private  String NAME, DESCRIPTION, itemName, itemDescription,itemType;

    private boolean isLocked;
    private int ID, ROOM_ID, ITEM_ID, HP, DAMAGE, itemID, itemRoomID, itemDamage,
    itemHeal,  itemHP, itemCriticalHit;

    /**
     * Assigns monster values based on values written in {@link #idFile}, {@link #idFile},
     *  {@link #roomIDFile}, {@link #itemIDFile}, {@link #hpFile}, {@link #damageFile},
     *  {@link #nameFile}, and {@link #descriptionFile}
     */
    public MonsterReader() {
        ID = Integer.parseInt(readAttribute(idFile));
        ROOM_ID = Integer.parseInt(readAttribute(roomIDFile));
        ITEM_ID = Integer.parseInt(readAttribute(itemIDFile));
        HP = Integer.parseInt(readAttribute(hpFile));
        DAMAGE = Integer.parseInt(readAttribute(damageFile));
        NAME = readAttribute(nameFile);
        DESCRIPTION = readAttribute(descriptionFile);
        isLocked = Boolean.parseBoolean(readAttribute(lockedFile));
        BufferedReader file = null;
        if(NAME.toLowerCase().contains("bonkrieg")) {
            file = bonkreigItem;
        }else if(NAME.toLowerCase().contains("high")){
            file = highItem;
        }else if(NAME.toLowerCase().contains("garold")){
            file = garoldItem;
        }else if(NAME.toLowerCase().contains("robert")){
            file = robertItem;
        }
        if(ITEM_ID > 0) {
            assert file != null;
            itemID = Integer.parseInt(readAttribute(file));
            itemName = readAttribute(file);
            itemDescription = readAttribute(file);
            itemRoomID = Integer.parseInt(readAttribute(file));
            itemDamage = Integer.parseInt(readAttribute(file));
            itemHeal = Integer.parseInt(readAttribute(file));
            itemType = readAttribute(file);
            itemHP = Integer.parseInt(readAttribute(file));
            itemCriticalHit = Integer.parseInt(readAttribute(file));
        }
    }

    public static BufferedReader getNameFile() {
        return nameFile;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemType() {
        return itemType;
    }

    public int getItemID() {
        return itemID;
    }

    public int getItemRoomID() {
        return itemRoomID;
    }

    public int getItemDamage() {
        return itemDamage;
    }

    public int getItemHeal() {
        return itemHeal;
    }

    public int getItemHP() {
        return itemHP;
    }

    public int getItemCriticalHit() {
        return itemCriticalHit;
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

    /**
     * If the stream is not empty, the method will continue to read and store
     * lines of text into a {@code StringBuilder} until the next read line
     * is equivalent to the given String delimiter "----"
     * @param file - The text file inside a {@code BufferedReader} stream
     * @return block of text between the delimiters in the file
     * @since 1.0
     */
    private String readAttribute(BufferedReader file) {
        StringBuilder attribute = new StringBuilder();

        try {
            String next = file.readLine();

            while (file.ready() && !next.equals("----")) {
                attribute.append(next);
                next = file.readLine();

                //Add a newline to the end of the current line if the next line is not the delimiter
                if (next.length() > 4) {
                    attribute.append("\n");
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return attribute.toString();
    }
}