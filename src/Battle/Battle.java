package Battle;

import Item.Item;
import Monster.Monster;
import Character.Character;

import java.util.LinkedList;
import java.util.List;
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
    public boolean attackMonster(List<Item> inventory) {
        //inventory is "itemController.model.get("0")
//        LinkedList<Item> playerInventory = (LinkedList<Item>) inventory;
        if (getPlayerHp() > 0) {
            monster.setHp(getMonsterHp() - getPlayerAttackPoints());

            //health restoration functionality
//            if(playerInventory.size() > 0) { //verifies that the player has items in their inventory
//
//                // Determines the total player health restoration points.
//                // If that value is greater than 0, then increase player health by that amount
//
//                int healthRestoration = playerHealthRestore(playerInventory, playerInventory.size() - 1);
//                if (healthRestoration > 0) {
//                    player.setHp(getPlayerHp() + healthRestoration);
//
//                }
//            }
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
        boolean isEquipped = inventory.get(length).getEquipped();
        String type = inventory.get(length).get_itemType();
        int restore = 0;
        for(Item item : inventory) {
            if(isEquipped && hp > 0 && type.equalsIgnoreCase("weapon")) {
                restore += hp;
            }
        }
        return restore;
    }


    /**
     * Determines if a monster can attack the player.  Sets the player health points during a
     * fight using the monster attack damage.  This method uses {@link Random} to generate a random
     * {@code int} to determine if the player can dodge or block an incoming attack.
     * @return if a monster is alive
     * @author Brian Smithers and Khamilah Nixon
     */
    public boolean attackPlayer(boolean selectDodge, boolean selectBlock) {
        if (getMonsterHp() > 0) {
            if(selectDodge && !playerDodge()){
                player.setHp(getPlayerHp() - getMonsterAttackPoints() + damageReduction(selectBlock));
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
    private int damageReduction(boolean playerSelectBlock) {
        if(playerSelectBlock){
            int randomBlockInt = Math.abs(new Random().nextInt());
            if (player.getBlockChance() > 0 && randomBlockInt % (1 / player.getBlockChance()) == 0) {
                return (int) (Math.random() * getMonsterHp() - 1) + 1;
            }
        }
        return 0;
    }

    // Used in the view
    /**
     * Determines the monster deal damage
     * @return monster damage
     * @author Khamilah Nixon
     */
    public int getMonsterAttackPoints() {
        return monster.getDAMAGE();
    }

    // Used in the view
    /**
     * Determines the player deal damage
     * @return player damage
     * @author Khamilah Nixon
     */
    public int getPlayerAttackPoints() {
        return player.getAttack();
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
