package Battle;

import Character.Character;
import Item.Item;
import Monster.Monster;

import java.io.Serializable;
import Utilities.ConsoleColors;

/**
 * Author: Brian Smithers
 */
public class BattleView implements Serializable {
    private ConsoleColors colors = new ConsoleColors();

    public void attackTurnResult(String attacker, int attackersAttackPoints, String defender) {
        System.out.printf(
                "%s did %d points of damage to %s\n", attacker, attackersAttackPoints,
                defender);
    }

    public void blockSuccessful(String attacker, String defender) {
        System.out.println(
                colors.textColor(attacker + " successfully blocked " + defender + "'s attack","b/green"));
    }

    public void blockUnsuccessful(String attacker, String defender) {
        System.out.println(
                colors.textColor(attacker + " did not block " + defender + "'s attack","red"));
    }

    public void dodgeSuccessful(String attacker, String defender) {
        System.out.println(
                colors.textColor(attacker + " successfully dodged " + defender + "'s attack","b/green"));
    }

    public void dodgeUnsuccessful(String attacker, String defender) {
        System.out.println(
                colors.textColor(attacker + " did not dodge " + defender + "'s attack","red"));
    }

    public void monsterSlayed(Monster monster) {

        System.out.println(
                colors.textColor(monster.getName() + " was slayed","red"));
    }

    public void cannotStartBattle() {

        System.out.println(colors.textColor("There is no monster to fight","red"));
    }

    public void exhaustTurn(String attacker) {
        System.out.println(
                colors.textColor(attacker + " exhausted their turn", "b/green"));
    }

    public void accessInventory(Character character) {
        System.out.println(
                colors.textColor(character.getName() + " gained a one-time usage to the equip, unequip, and" +
                " use commands without exhausting their turn", "b/green"));
    }

    public void monsterDropItem(Monster monster, Item drop) {
        System.out.println(
                colors.textColor(monster.getName() + " dropped " + drop.get_itemName(),"red"));
    }

    public void battleCommands() {
        System.out.println("Attack, Dodge, or Block");
    }

    /**
     * Author: Khamilah Nixon
     */
    public void remainingHealth(Monster monster, Character character) {
        System.out.println(character.getName() + "'s Remaining Health: " + character.getCurrentHitPoints());
        System.out.println(monster.getName() + "'s Remaining Health: " + monster.getHp());
    }
}
