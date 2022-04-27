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
        if(model.getMonster() != null) {
            if (model.getMonster().getHp() > 0 && model.getPlayer().getHp() > 0) {
                // Player attacks monster
                if (playerSelectedBlock) {
                    //Determines the amount of damage reduction
                    //If a player successfully blocks an attack, then the incoming
                    //damage will be reduced
                    int damageReduction = model.blockPlayer();
                    if (damageReduction > 0) {
                        view.blockSuccessful(model.getPlayerName(), model.getMonsterName());
                        int totalMonsterDamage = model.getMonsterAttackPoints() - damageReduction;
                        model.attackMonster();
                        view.attackTurnResult(model.getPlayerName(), model.getPlayerAttackPoints(), model.getMonsterName());
                        view.attackTurnResult(model.getMonsterName(),
                                Math.max(totalMonsterDamage, 0), model.getPlayerName());
                    } else {
                        view.blockUnsuccessful(model.getPlayerName(), model.getMonsterName());
                    }
                } else if (playerSelectedDodge) {
                    //Determines if a player successfully dodged an attack
                    //If a player successfully dodges an attack, then a monster
                    //will not attack the player
                    if (model.dodgePlayer()) {
                        view.dodgeSuccessful(model.getPlayerName(), model.getMonsterName());
                        model.attackMonster();
                        view.attackTurnResult(model.getPlayerName(), model.getPlayerAttackPoints(), model.getMonsterName());
                        view.attackTurnResult(model.getMonsterName(), 0, model.getPlayerName());
                    } else {
                        view.dodgeUnsuccessful(model.getPlayerName(), model.getMonsterName());
                    }
                } else { //if a player uses attack command
                    model.attackMonster();
                    view.attackTurnResult(model.getPlayerName(), model.getPlayerAttackPoints(), model.getMonsterName());
                    if(model.getMonster().getHp() <= 0) {
                        view.monsterSlayed(model.getMonster());
                        return;
                    }
                    model.attackPlayer();
                    view.attackTurnResult(model.getMonsterName(), model.getMonsterAttackPoints(), model.getPlayerName());

                }
                // Print remaining health for both monster and player
                view.remainingHealth(model.getMonster(), model.getPlayer());
            }else {
                view.cannotStartBattle();
            }
        }

    }

    /**
     * @author Khamilah Nixon
     */
    public Battle getModel() {
        return model;
    }
}
