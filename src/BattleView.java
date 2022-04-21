/**
 * Author: Brian Smithers
 */
public class BattleView {
    public void attackTurnResult(String attacker, int attackersAttackPoints, String defender) {
        System.out.printf("%s did %d points of damage to %s", attacker, attackersAttackPoints,
                defender);
    }
}
