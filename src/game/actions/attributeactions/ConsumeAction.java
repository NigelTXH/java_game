package game.actions.attributeactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.abilities.Ability;

/**
 * A class used to regain the player's stamina
 * @author Nigel Tan
 */
public class ConsumeAction extends Action {

    private int amount;
    private Item item;
    private BaseActorAttributes baseActorAttributes;

    private Ground ground;
    /**
     * Constructor for StaminaIncreaseAction.
     * @param amount number to regain the player's stamina by.
     * @param item the item to be dropped.
     */
    public ConsumeAction(int amount, Item item, BaseActorAttributes baseActorAttributes){
        this.item = item;
        this.amount = amount;
        this.baseActorAttributes = baseActorAttributes;
    }

    public ConsumeAction(int amount, Ground ground, BaseActorAttributes baseActorAttributes){
        this.amount = amount;
        this.ground = ground;
        this.baseActorAttributes = baseActorAttributes;
    }

    /**
     * When executed, will regain an attribute of the player.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing that healing occur.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (this.item != null) {
            actor.removeItemFromInventory(item);
        }
        actor.modifyAttribute(baseActorAttributes, ActorAttributeOperations.INCREASE, amount);

        return menuDescription(actor);
    }
    /**
     * A string describing the action suitable for displaying in the UI menu.
     * @param actor The actor performing the action.
     * @return a String, e.g. "Increase stamina by 40".
     */
    @Override
    public String menuDescription(Actor actor) {
        if (this.ground != null && ground.hasCapability(Ability.DRINKABLE)){
            return actor +" drinks from "+ground+ " and restores " + this.amount;
        }
        return "Regain " + amount + " " + baseActorAttributes;
    }
}
