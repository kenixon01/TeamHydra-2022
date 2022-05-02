package Battle;

import Item.Item;
import Monster.Monster;
import Character.Character;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

/**
 * Author: Brian Smithers
 */
public class Battle implements Serializable {
    private final Character player;
    private final Monster monster;

    public Battle(Character player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    public int loseHealth(int health, int damage) {
        if (health - damage < 0) {
            health = 0;
        } else {
            health -= damage;
        }
        return health;
    }

    /**
     * Author: Khamilah Nixon
     */
    public int gainHealth(int health, int maxHealth, int restoration) {
        if (health + restoration > maxHealth) {
            health = maxHealth;
        } else {
            health += restoration;
        }
        return health;
    }

    /**
     * Determines if a player can attack the monster.  Sets the monster health points
     * during the player's attack.  If the player equipped an item that can
     * regenerate player health after attacking, then the player's health
     * points will increase accordingly.
     *
     * @return if the player is alive
     * @author Brian Smithers and Khamilah Nixon
     */
    public boolean attackMonster() {
//        inventory = itemController.model.get("0");
        LinkedList<Item> playerInventory = player.getInventoryController().getItemInventory();
        if (getPlayerHp() > 0 && getMonsterHp() > 0) {
            monster.setHp(loseHealth(getMonsterHp(), getPlayerAttackPoints()));

//            health restoration functionality
            assert playerInventory != null;
            if (playerInventory.size() > 0) {
                int healthRestoration = playerHealthRestore(playerInventory, playerInventory.size() - 1);
                if (healthRestoration > 0) {
                    player.setCurrentHitPoints(gainHealth(getPlayerHp(), getPlayer().getMaxHitPoints(), healthRestoration));

                }
            }
            if (getMonsterHp() <= 0 && monster.getInventory() != null) {
                Item item = getMonster().getInventory().getItemInventory().getFirst();
                getPlayer().getInventoryController().transferItem(
                        "Remove this", "remove this",
                        getMonster().getInventory().getItemInventory(), item.get_itemName(), null);
            }
            return true;
        }
        Item item = monster.getInventory().getItemInventory().getFirst();
        getPlayer().getInventoryController().transferItem("NEVER USED","NEVER USED",
                monster.getInventory().getItemInventory(),item.get_itemName(), null);
        return false;
    }

    /**
     * This method will determine to total amount of health points the player will
     * receive if they use an item that restores health during a fight
     *
     * @param inventory Array of equipped inventory items
     * @param length    size of inventory
     * @return total health points restored to player
     * @author Khamilah Nixon
     */    // This is for the shadow bow
    private int playerHealthRestore(LinkedList<Item> inventory, int length) {
        // run through the linkedlist array
        // look for an item that is of weapon type and has a positive health restoration value
        // get that item
        int restore = 0;
        for (Item item : inventory) {
            if (item.get_healValue() > 0 && item.getEquipped()) {
                restore += item.get_healValue();
            }
        }
        return restore;

//        int hp = inventory.get(length).get_healValue();
//        boolean isEquipped = inventory.get(length).getEquipped();
//        String type = inventory.get(length).get_itemType();
//        for(Item item : inventory) {
//            if(isEquipped && hp > 0 && type.equalsIgnoreCase("weapon")) {
//            }
//        }
    }


    /**
     * Determines if a monster can attack the player.  Sets the player health points during a
     * fight using the monster attack damage.  This method uses {@link Random} to generate a random
     * {@code int} to determine if the player can dodge or block an incoming attack.
     *
     * @return if a monster is alive
     * @author Brian Smithers and Khamilah Nixon
     */
    public boolean attackPlayer() {
        if (getPlayerHp() > 0 && getMonsterHp() > 0) {
            player.setCurrentHitPoints(loseHealth(getPlayerHp(), getMonsterAttackPoints()));
            return true;
        }
        return false;
    }

    /**
     * Determines if the player dodges an attack.  This is dependent on the player's dodge chance:
     * If the player successfully dodges, then they will absorb all incoming damage.
     *
     * @return if the player dodges an incoming monster attack
     * @author Khamilah Nixon
     */
    public boolean dodgePlayer() {
        int randomDodgeInt = Math.abs(new Random().nextInt());
        return (player.getDodgeChance() > 0 && (randomDodgeInt % (1 / player.getDodgeChance()) == 0));
    }

    /**
     * Calculates the amount of damage reduction from an incoming monster attack if the player
     * has a block chance percentage.
     *
     * @return damage reduction
     * @author Khamilah Nixon
     */
    public int damageReduction() {
        int randomBlockInt = Math.abs(new Random().nextInt());
        if (randomBlockInt % 4 == 0) {
            return (int) (Math.random() * getMonsterAttackPoints() - 1) + 1;
        }
        return 0;
    }

    // Used in the view

    /**
     * Determines the monster deal damage
     *
     * @return monster damage
     * @author Khamilah Nixon
     */
    public int getMonsterAttackPoints() {
        return monster.getDamage();
    }

    // Used in the view

    /**
     * Determines the player deal damage
     *
     * @return player damage
     * @author Khamilah Nixon
     */
    public int getPlayerAttackPoints() {
        return player.getAttack();
    }

    // Used in the view
    public int getPlayerHp() {
        return player.getCurrentHitPoints();
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
        return monster.getName();
    }

    public Character getPlayer() {
        return player;
    }

    public Monster getMonster() {
        return monster;
    }
}
