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
        // Print remaining health for both monster and player
//        view.remainingHealth(model.getMonster(),model.getPlayer());
        if(model.getMonster().getHp() > 0 && model.getPlayer().getHp() > 0) {
            // Player attacks monster
            model.attackMonster();
            view.attackTurnResult(model.getPlayerName(), model.getPlayerAttackPoints(), model.getMonsterName());

            model.attackPlayer(playerSelectedDodge, playerSelectedBlock);
            // Monster attacks player
            view.attackTurnResult(model.getMonsterName(), model.getMonsterAttackPoints(), model.getPlayerName());

            // Print remaining health for both monster and player
            view.remainingHealth(model.getMonster(), model.getPlayer());
        }
    }

    /**
     * @author Khamilah Nixon
     */
    public Battle getModel() {
        return model;
    }
}
