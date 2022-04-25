package Battle;

import Character.Character;
import Monster.Monster;

/**
 * Author: Brian Smithers
 */
public class BattleView {
    public void attackTurnResult(String attacker, int attackersAttackPoints, String defender) {
        System.out.printf("%s did %d points of damage to %s\n", attacker, attackersAttackPoints,
                defender);
    }

    /**
     * Author: Khamilah Nixon
     */
    public void remainingHealth(Monster monster, Character character) {
        System.out.println(character.getName() + "'s Remaining Health: " + character.getHp());
        System.out.println(monster.getName() + "'s Remaining Health: " + monster.getHp());
    }

    /**
     * Author: Khamilah Nixon
     */
    public void printMonsterDead(){
        System.out.println("Monster died");
    }
}
