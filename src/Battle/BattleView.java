package Battle;

import Character.Character;
import Monster.Monster;

import java.io.Serializable;

/**
 * Author: Brian Smithers
 */
public class BattleView implements Serializable {
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

    public void dodgeUnsuccessful(String attacker, String defender) {
        System.out.println(attacker + " did not dodge " + defender + "'s attack");
    }

    public void monsterSlayed(Monster monster) {
        System.out.println(monster.getName() + " was slayed");
    }
    public void cannotStartBattle() {
        System.out.println("There is no monster to fight");
    }

    public void exhaustTurn(String attacker) {
        System.out.println(attacker + " exhausted their turn");
    }

    public void accessInventory(Character character) {
        System.out.println(character.getName() + " gained a one-time usage to the equip, unequip, and" +
                " use commands without exhausting their turn");
    }

    public void battleCommands() {
        System.out.println("Attack, Dodge, or Block");
    }

    /**
     * Author: Khamilah Nixon
     */
    public void remainingHealth(Monster monster, Character character) {
        System.out.println(character.getName() + "'s Remaining Health: " + character.getHp());
        System.out.println(monster.getName() + "'s Remaining Health: " + monster.getHp());
    }

    public void gameOver() {
        System.out.println("Game Over");
    }
}
