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
    public void printBattleDetails(List<Item> inventory, boolean playerSelectedDodge, boolean playerSelectedBlock) {
        // Player attacks monster
        if (model.attackMonster(inventory)) {
            view.attackTurnResult(model.getPlayerName(),
                    model.getPlayerAttackPoints(), model.getMonsterName());
        }
        // Monster.Monster attacks player
        if (model.attackPlayer(playerSelectedDodge, playerSelectedBlock)) {
            view.attackTurnResult(model.getMonsterName(),
                    model.getMonsterAttackPoints(), model.getPlayerName());
        }
    }
}
