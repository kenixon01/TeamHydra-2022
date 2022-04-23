package Monster;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Monster.MonsterReader class defines a monster's attributes and assigns values to those attributes
 * by reading multiple text files. All monsters are constants, and instances of this class
 * cannot alter their initial states.  All monsters have the following attributes:
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
 * @version 1.0
 */
public final class MonsterReader {

    private static BufferedReader idFile, roomIDFile, itemIDFile, hpFile, damageFile, nameFile, descriptionFile;

    private final int ID, ROOM_ID, ITEM_ID, HP, DAMAGE;

    private final String NAME, DESCRIPTION;

    static {
        try {
            idFile = new BufferedReader(new FileReader("src/Monster.Monster.MonsterTextFiles/ID.txt"));
            roomIDFile = new BufferedReader(new FileReader("src/Monster.Monster.MonsterTextFiles/RoomID.txt"));
            itemIDFile = new BufferedReader(new FileReader("src/Monster.Monster.MonsterTextFiles/ItemID.txt"));
            hpFile = new BufferedReader(new FileReader("src/Monster.Monster.MonsterTextFiles/HP.txt"));
            damageFile = new BufferedReader(new FileReader("src/Monster.Monster.MonsterTextFiles/Damage.txt"));
            nameFile = new BufferedReader(new FileReader("src/Monster.Monster.MonsterTextFiles/Name.txt"));
            descriptionFile = new BufferedReader(new FileReader("src/Monster.Monster.MonsterTextFiles/Description.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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