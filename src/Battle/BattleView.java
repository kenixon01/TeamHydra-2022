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

    public void blockSuccessful(String attacker, String defender) {
        System.out.println(attacker + " successfully blocked " + defender + "'s attack");
    }

    public void blockUnsuccessful(String attacker, String defender) {
        System.out.println(attacker + " did not block " + defender + "'s attack");
    }

    public void dodgeSuccessful(String attacker, String defender) {
        System.out.println(attacker + " successfully dodged " + defender + "'s attack");
    }

    public void dodgeUnuccessful(String attacker, String defender) {
        System.out.println(attacker + " did not dodge " + defender + "'s attack");
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
        System.out.println("There is no monster here");
    }
}
