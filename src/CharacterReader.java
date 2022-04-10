import java.util.LinkedList;

public class CharacterReader extends Character {

    private String CHAR_SOURCE;

    public CharacterReader(int id, String name, LinkedList<Item> playerItemInventory,
                           String description, int hitPoints, double dodgeChance,
                           int damage) {
        super(id, name, playerItemInventory, description, hitPoints, dodgeChance, damage);
    }
}
