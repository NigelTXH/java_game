package game.actions.tradeactions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.trademechanic.TradeItem;
/**
 * An abstract class representing generic sell actions.
 * Created by:
 * @author Nigel Tan
 */
public abstract class SellAction extends TradeAction {
    private boolean sold;


    /**
     * Constructor for SellAction.
     * @param price        price of item
     * @param chance chance of the special event occurring
     * @param tradeItem   the item to be traded
     */
    public SellAction(int price, int chance, TradeItem tradeItem){
        super(price, chance, tradeItem);
    }

    /**
     * A method used to remove the item from the player
     * if the item exists in the player's inventory.
     * @param actor the actor trading with the trader
     */
    @Override
    public void transaction(Actor actor){
        for(Item item:actor.getItemInventory()){
            if(super.getTradeItem().itemInTrade(item)){
                sold = true;
            }
        }
        if(sold){
            this.removeFromInventory(actor);
        }
    }

    /**
     * A method used to remove the item from an actor's inventory.
     * @param actor the actor trading with the trader
     */
    public void removeFromInventory(Actor actor){
        super.getTradeItem().removeFromInventory(actor);
    }

    /**
     * A getter for the sold attribute
     * @return the sold attribute
     */
    public boolean isSold() {
        return sold;
    }

    /**
     * A method to display the result of the transaction and set the price back to the original.
     * @param actor the actor trading with the trader
     * @return a result depending if the item sold successfully
     */
    @Override
    public String endTransaction(Actor actor){
        String result;
        if(sold){
            result = actor + " obtains " + super.getPrice() + " runes from " +  super.getTradeItem();
        }
        else{
            result = actor + " does not have " + super.getTradeItem() + " so nothing happend";
        }
        sold = false;
        super.setPrice(super.getOriginalPrice());
        return result;
    }

    /**
     * A method for displaying the option to sell to the player.
     * @param actor the actor trading with the trader
     * @return Sell item for price
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Sell " + super.getTradeItem() + " for " + super.getPrice();
    }

}
