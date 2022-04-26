package Battle;

import Item.Item;

import java.util.List;

/**
 *  Author: Brian Smithers
 */
public class BattleController {

    private Battle model;
    private BattleView view;

    public BattleController(Battle model, BattleView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Allows player and monster to engage in battle.  Displays
     * attack damage information after each attack.
     * @author Brian Smithers and Khamilah Nixon
     */
    public void printBattleDetails(List<Item> inventory, boolean playerSelectedDodge,
                                   boolean playerSelectedBlock) {
        if(model.getMonster().getHp() > 0 && model.getPlayer().getHp() > 0) {
            // Player attacks monster
            if(playerSelectedBlock) {
                int damageReduction = model.blockPlayer();
                if(damageReduction > 0){
                    view.blockSuccessful(model.getPlayerName(), model.getMonsterName());
                    int totalMonsterDamage = model.getMonsterAttackPoints() - damageReduction;
                    view.attackTurnResult(model.getMonsterName(),
                            Math.max(totalMonsterDamage, 0), model.getPlayerName());
                }
                else {
                    view.blockUnsuccessful(model.getPlayerName(), model.getMonsterName());
                }
            }
            else if(playerSelectedDodge) {
                if (model.dodgePlayer()) {
                    view.dodgeSuccessful(model.getPlayerName(), model.getMonsterName());
                    view.attackTurnResult(model.getMonsterName(), 0, model.getPlayerName());
                }
                else {
                    view.dodgeUnuccessful(model.getPlayerName(), model.getMonsterName());
                }
//                else {
//
//                }
            }
            else {
                model.attackMonster();
                view.attackTurnResult(model.getPlayerName(), model.getPlayerAttackPoints(), model.getMonsterName());
                model.attackPlayer();
                view.attackTurnResult(model.getMonsterName(), model.getMonsterAttackPoints(), model.getPlayerName());
            }
            // Monster attacks player

            // Print remaining health for both monster and player
            view.remainingHealth(model.getMonster(), model.getPlayer());
        }
    }

    /**
     * Author: Khamilah Nixon
     */
    public void monsterDead() {
        view.printMonsterDead();
    }

    /**
     * @author Khamilah Nixon
     */
    public Battle getModel() {
        return model;
    }
}
