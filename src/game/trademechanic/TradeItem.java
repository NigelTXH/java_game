package game.trademechanic;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * A class used to collect information of the item as well as cloning the TradeItem and
 * checking if the player's item is the same.
 * @author Nigel Tan
 */
public class TradeItem {
    private final Item item;

    /**
     * Constructor for TradeItem.
     * Also adds a capability to the item that it can be traded.
     * @param item the item to be added
     */
    public TradeItem(Item item){
        this.item = item;
        item.addCapability(ItemTrade.TRADABLE);
    }

    /**
     * A method used to check if the item in the parameter is the same
     * as the item in this class.
     * @param item the item being checked
     * @return true if the item is identical, false otherwise
     */
    public boolean itemInTrade(Item item){
        return (this.item == item);
    }

    /**
     * A method used to add the item to an actor's inventory.
     * @param actor the actor trading with the trader
     */
    public void addToInventory(Actor actor){
        actor.addItemToInventory(item);
    }

    /**
     * A method used to remove the item from an actor's inventory.
     * @param actor the actor trading with the trader
     */
    public void removeFromInventory(Actor actor){
        actor.removeItemFromInventory(item);
    }

    /**
     * A method for cloning the TradeItem.
     * @return a copy of the TradeItem
     */
    public TradeItem clone(){
        return new TradeItem(item);
    }

    /**
     * A method to print the item in string form.
     * @return the item in string form
     */
    @Override
    public String toString() {
        return item.toString();
    }
}
