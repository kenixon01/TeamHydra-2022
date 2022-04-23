package Battle;

import Battle.Battle;

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

    public void printBattleDetails() {
        // Player attacks monster
        if (model.attackMonster()) {
            view.attackTurnResult(model.getPlayerName(),
                    model.getPlayerAttackPoints(), model.getMonsterName());
        }
        // Monster.Monster attacks player
        if (model.attackPlayer()) {
            view.attackTurnResult(model.getMonsterName(),
                    model.getMonsterAttackPoints(), model.getPlayerName());
        }
    }
}
