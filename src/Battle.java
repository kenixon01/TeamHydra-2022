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

    public boolean attackMonster() {
        if (player.getHp() > 0) {
            monster.setHp(monster.getHp() - player.getAttack());
            return true;
        }
        return false;
    }

    public boolean attackPlayer() {
        if (monster.getHp() > 0) {
            player.setHp(player.getHp() - monster.getAttack());
            return true;
        }
        return false;
    }

    // Used in the view
    public int getMonsterHp() {
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
        return monster.getName();
    }
}
