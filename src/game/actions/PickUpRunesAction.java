package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * The PickUpRunesAction class represents an action performed by an actor to pick up a specified quantity of runes and add them to their balance.
 * It extends the PickUpAction class, allowing an actor to pick up runes as items and increase their in-game balance.
 *
 * The action involves adding the specified quantity of runes to the actor's balance and removing the runes from the game map.
 * A message is displayed to inform the player of the change in their balance.
 *
 * @author Sam Leong
 */
public class PickUpRunesAction extends PickUpAction {
    private final int quantity;
    private final Item item;

    /**
     * Constructor for the PickUpRunesAction class.
     * Initializes the action with a specified quantity of runes to be picked up and the item representing the runes.
     *
     * @param quantity The quantity of runes to be picked up.
     * @param item     The item representing the runes.
     */
    public PickUpRunesAction(int quantity, Item item){
        super(item);
        this.quantity = quantity;
        this.item = item;
    }

    /**
     * Executes the PickUpRunesAction, adding the specified quantity of runes to the actor's balance
     * and removing the runes from the game map.
     *
     * @param actor The actor performing the action.
     * @param map   The game map in which the action takes place.
     * @return A message indicating the actor's balance increase and the new balance value.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addBalance(this.quantity);
        map.locationOf(actor).removeItem(item);
        return actor + " balance increased by "+ this.quantity+", and now has "+ actor.getBalance();
    }

    /**
     * Provides a description of the action to be displayed in the game menu.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action as "actor pick up runes and add to balance."
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " pick up runes and add to balance";
    }
}
