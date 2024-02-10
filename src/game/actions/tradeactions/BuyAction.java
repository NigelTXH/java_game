package game.actions.tradeactions;

import edu.monash.fit2099.engine.actors.Actor;
import game.trademechanic.TradeItem;

/**
 * An abstract class representing generic buy actions.
 * Created by:
 * @author Nigel Tan
 */
public abstract class BuyAction extends TradeAction {
    private boolean paid;

    /**
     * Constructor for BuyAction.
     * @param price        price of item
     * @param chance chance of the special event occurring
     * @param tradeItem   the item to be traded
     */
    public BuyAction(int price, int chance, TradeItem tradeItem){
        super(price, chance, tradeItem);
    }

    /**
     * A method used to deduct balance from the player's wallet
     * if the player has enough money.
     * @param actor the actor trading with the trader
     */
    @Override
    public void transaction(Actor actor){
        if(actor.getBalance() >= super.getPrice()) {
            paid = true;
            actor.deductBalance(super.getPrice());
        }
    }

    /**
     * A method used to add the item to an actor's inventory.
     * @param actor the actor trading with the trader
     */
    public void addToInventory(Actor actor){
        super.getTradeItem().addToInventory(actor);
    }
    /**
     * A getter for the paid attribute
     * @return the paid attribute
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * A method to display the result of the transaction and set the price back to the original.
     * @param actor the actor trading with the trader
     * @return a result depending if the item paid successfully
     */
    @Override
    public String endTransaction(Actor actor){
        String result;
        if(paid){
            result = actor + " pays " + super.getPrice() + " runes for " +  super.getTradeItem();
        }
        else{
            result = actor + " cannot afford " + super.getPrice() + " runes for " + super.getTradeItem();
        }
        paid = false;
        super.setPrice(super.getOriginalPrice());
        return result;
    }

    /**
     * A method for displaying the option for the player to buy.
     * @param actor the actor trading with the trader
     * @return Buy item for price
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy " + super.getTradeItem() + " for " + super.getPrice();
    }

}
