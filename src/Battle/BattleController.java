package Battle;

import Item.Item;

import java.util.List;

/**
 *  Author: Brian Smithers
 */
public class BattleController {

    private Battle model;
    private BattleView view;

    private boolean isSuccessfulHit;

    public BattleController(Battle model, BattleView view) {
        this.model = model;
        this.view = view;
        this.isSuccessfulHit = false;
    }

    /**
     * Determines the amount of damage reduction
     * If a player successfully blocks an attack, then the incoming
     * damage will be reduced
     * @author Brian Smithers and Khamilah Nixon
     */
    private void playerBlock() {
        int damageReduction = model.blockPlayer();
        if (damageReduction > 0) {
            view.blockSuccessful(model.getPlayerName(), model.getMonsterName());
            int totalMonsterDamage = model.getMonsterAttackPoints() - damageReduction;
            model.attackMonster();
            view.attackTurnResult(model.getPlayerName(), model.getPlayerAttackPoints(), model.getMonsterName());
            view.attackTurnResult(model.getMonsterName(),
                    Math.max(totalMonsterDamage, 0), model.getPlayerName());
            isSuccessfulHit = true;
            view.accessInventory(model.getPlayer());

        }
        else {
            view.blockUnsuccessful(model.getPlayerName(), model.getMonsterName());
            model.attackPlayer();
            view.attackTurnResult(model.getMonsterName(), model.getMonsterAttackPoints(), model.getPlayerName());
            isSuccessfulHit = false;
        }
        view.remainingHealth(model.getMonster(), model.getPlayer());
    }

    /**
     * Determines if a player successfully dodged an attack
     * If a player successfully dodges an attack, then a monster
     * will not attack the player
     * @author Brian Smithers and Khamilah Nixon
     */
    private void playerDodge() {
        if (model.dodgePlayer()) {
            view.dodgeSuccessful(model.getPlayerName(), model.getMonsterName());
            isSuccessfulHit = true;
            view.accessInventory(model.getPlayer());

        }
        else {
            view.dodgeUnsuccessful(model.getPlayerName(), model.getMonsterName());
            model.attackPlayer();
            view.attackTurnResult(model.getMonsterName(), model.getMonsterAttackPoints(), model.getPlayerName());
            isSuccessfulHit = false;
        }
        view.remainingHealth(model.getMonster(), model.getPlayer());
    }

    /**
     * Allows player to use attack command
     * @author Brian Smithers and Khamilah Nixon
     */
    private void playerAttack() {
        model.attackMonster();
        view.attackTurnResult(model.getPlayerName(), model.getPlayerAttackPoints(), model.getMonsterName());
        if(model.getMonster().getHp() <= 0) {
            view.monsterSlayed(model.getMonster());
            return;
        }
        model.attackPlayer();
        view.attackTurnResult(model.getMonsterName(), model.getMonsterAttackPoints(), model.getPlayerName());
        view.remainingHealth(model.getMonster(), model.getPlayer());
        isSuccessfulHit = false;
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
                if (playerSelectedBlock) {
                    playerBlock();
                } else if (playerSelectedDodge) {
                    playerDodge();
                } else {
                    playerAttack();
                }
                if(!isSuccessfulHit) {

                    view.battleCommands();
                }
            }else {
                view.cannotStartBattle();
            }
        }

    }

    /**
     * @author Khamilah Nixon
     */
    public void exhaustTurn() {
        if(!isSuccessfulHit) {
            view.exhaustTurn(model.getPlayerName());
            model.attackPlayer();
            view.attackTurnResult(model.getMonsterName(), model.getMonsterAttackPoints(), model.getPlayerName());
        }
        else {
            isSuccessfulHit = false;
        }
    }

    public Battle getModel() {
        return model;
    }
}
