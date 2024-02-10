package game.actions.blacksmithactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.blacksmith.BlackSmithEnum;
import game.buffactions.BuffAttributes;
import game.upgrade.UpgradableItemInterface;


/**
 * Class representing how an upgrade will be performed if the player chooses this action. It is capable
 * of upgrading a stat depending on by the amount to a certain item when the conditions are right.
 * Created by:
 * @author Nigel Tan
 * Modified by:
 * @author Nigel Tan
 */
public class UpgradableActions extends Action {
    private final UpgradableItemInterface item;
    private final BuffAttributes buffAttribute;
    private final float amount;
    private final int runes;
    private final BlackSmithEnum blackSmithEnum;
    private final int levelCap;

    /**
     * The constructor of the UpgradableActions class without level cap.
     * @param item The item to upgrade
     * @param buffAttribute The type of buff to upgrade with
     * @param amount How much to increase the item by
     * @param runes How expensive the upgrade costs
     * @param blackSmithEnum The blacksmith to check if nearby
     */
    public UpgradableActions(UpgradableItemInterface item, BuffAttributes buffAttribute, float amount, int runes, BlackSmithEnum blackSmithEnum){
        this.item = item;
        this.buffAttribute = buffAttribute;
        this.amount = amount;
        this.runes = runes;
        this.blackSmithEnum = blackSmithEnum;
        this.levelCap = -1;
    }

    /**
     * The constructor of the UpgradableActions class without level cap.
     * @param item The item to upgrade
     * @param buffAttribute The type of buff to upgrade with
     * @param amount How much to increase the item by
     * @param runes How expensive the upgrade costs
     * @param blackSmithEnum The blacksmith to check if nearby
     * @param levelCap The item cannot go higher than the levelCap
     */
    public UpgradableActions(UpgradableItemInterface item, BuffAttributes buffAttribute, float amount, int runes, BlackSmithEnum blackSmithEnum, int levelCap){
        this.item = item;
        this.buffAttribute = buffAttribute;
        this.amount = amount;
        this.runes = runes;
        this.blackSmithEnum = blackSmithEnum;
        this.levelCap = levelCap;
    }



    /**
     * A method executed by the player to upgrade the weapon. This function
     * checks if the player has enough runes before proceeding
     * with the upgrade by levelling up the item as well as raising a stat.
     * @param actor the actor performing this action
     * @param map the GameMap
     * @return the result.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor.getBalance() >= runes) {
            actor.deductBalance(runes);
            item.levelUp();
            item.raiseBuffAttributeBy(buffAttribute, amount);
            return item + " has been upgraded by " + amount;
        }

        return "You are too poor";

    }

    /**
     * Displays the action to upgrade a certain item unless it has a level cap or if the item does not have the buff attribute.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player picks up the rock"
     */
    @Override
    public String menuDescription(Actor actor) {
        if(item.checkItemHasAttribute(buffAttribute) == false || item.getLevel() == levelCap){
            return null;
        }

        if(item.findBlackSmithToTalkTo(blackSmithEnum)){
            return "Upgrade " + item + "'s " + buffAttribute + " by " + amount + " for " + runes;
        }

        return null;

    }
}
