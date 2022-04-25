package Battle;

/**
 * Author: Brian Smithers
 */
public class BattleView {
    public void attackTurnResult(String attacker, int attackersAttackPoints, String defender) {
        System.out.printf("%s did %d points of damage to %s\n", attacker, attackersAttackPoints,
                defender);
    }
}
