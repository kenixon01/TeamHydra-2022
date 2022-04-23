package Battle;

import Item.Item;
import Monster.Monster;
import Character.Character;

import java.util.LinkedList;
import java.util.Random;

/**
 * Author: Brian Smithers
 */
public class Battle {
    private final Character player;
    private final Monster monster;

    public Battle(Character player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    /**
     * Determines if a player can attack the monster.  Sets the monster health points
     * during the player's attack.  If the player equipped an item that can
     * regenerate player health after attacking, then the player's health
     * points will increase accordingly.
     * @return if the player is alive
     * @author Brian Smithers and Khamilah Nixon
     */
    public boolean attackMonster() {
        LinkedList<Item> inventory = player.getEquippedItems();
        if (getPlayerHp() > 0) {
            monster.setHp(getMonsterHp() - player.getAttack());
            player.setHp(getPlayerHp() + playerHealthRestore(inventory, inventory.size() - 1));
            return true;
        }
        return false;
    }

    /**
     * This method will determine to total amount of health points the player will
     * receive if they use an item that restores health during a fight
     * @param inventory Array of equipped inventory items
     * @param length size of inventory
     * @return total health points restored to player
     * @author Khamilah Nixon
     */
    private int playerHealthRestore(LinkedList<Item> inventory, int length) {
        int hp = inventory.get(length).get_healValue();
        if(length == 0) {
            return hp;
        }
        return hp + playerHealthRestore(inventory, length - 1);
    }


    /**
     * Determines if a monster can attack the player.  Sets the player health points during a
     * fight using the monster attack damage.  This method uses {@link Random} to generate a random
     * {@code int} to determine if the player can dodge or block an incoming attack.
     * @return if a monster is alive
     * @author Brian Smithers and Khamilah Nixon
     */
    public boolean attackPlayer() {
        if (getMonsterHp() > 0) {
            if(!playerDodge()){
                player.setHp(getPlayerHp() - monster.getDAMAGE() + damageReduction());
            }
            return true;
        }
        return false;
    }

    /**
     * Determines if the player dodges an attack.  This is dependent on the player's dodge chance:
     * If the player successfully dodges, then they will absorb all incoming damage.
     * @return if the player dodges an incoming monster attack
     * @author Khamilah Nixon
     */
    private boolean playerDodge() {
        int randomDodgeInt = Math.abs(new Random().nextInt());
        return (player.getDodgeChance() > 0 && (randomDodgeInt % (1 / player.getDodgeChance()) == 0));
    }

    /**
     * Calculates the amount of damage reduction from an incoming monster attack if the player
     * has a block chance percentage.
     * @return damage reduction
     * @author Khamilah Nixon
     */
    private int damageReduction() {
        int randomBlockInt = Math.abs(new Random().nextInt());
        if (player.getBlockChance() > 0 && randomBlockInt % (1 / player.getBlockChance()) == 0) {
            return (int) (Math.random() * getMonsterHp() - 1) + 1;
        }
        return 0;
    }

    // Used in the view
    public int getPlayerHp() {
        return player.getHp();
    }

    // Used in the view
    public int getMonsterHp() {
        return monster.getHp();
    }

    // Used in the view
    public String getPlayerName() {
        return player.getName();
    }

    // Used in the view
    public String getMonsterName() {
        return monster.getNAME();
    }
}
