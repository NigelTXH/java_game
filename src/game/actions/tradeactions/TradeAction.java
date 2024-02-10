package game.actions.tradeactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.trademechanic.TradeItem;

import java.util.Random;

/**
 * An abstract class representing what would happen during a trade. This includes how the transaction happens
 * and every TradeActions stores the price, the original price, the item to be traded and the chance for a
 * special event to occur.
 * Created by:
 * @author Nigel Tan
 */
public abstract class TradeAction extends Action {
    private int price;
    private TradeItem tradeItem;
    private int originalPrice;
    private int chance;

    /**
     * Constructor for TradeAction.
     * @param price        price of item
     * @param chance chance of the special event occurring
     * @param tradeItem   the item to be traded
     */
    public TradeAction(int price, int chance, TradeItem tradeItem){
        this.price = price;
        this.originalPrice = price;
        this.chance = chance;
        this.tradeItem = tradeItem;
    }

    /**
     * A setter for price attribute.
     */
    public void setPrice(int price){
        this.price = price;
    }
    /**
     * A getter for the chance attribute.
     * @return the chance.
     */
    public int getChance() {
        return chance;
    }
    /**
     * A getter for the price attribute.
     * @return the price.
     */
    public int getPrice() {
        return price;
    }
    /**
     * A getter for the original price attribute.
     * @return the original price.
     */
    public int getOriginalPrice(){
        return originalPrice;
    }
    /**
     * A getter for the tradeItem attribute.
     * @return a copy of the tradeItem.
     */
    public TradeItem getTradeItem(){
        return tradeItem.clone();
    }

    /**
     * Check if a special event has occurred.
     * @return a boolean indicating if a special event occurred
     */
    public boolean checkRNG(){
        Random rand = new Random();
        return rand.nextInt(1,100)<=chance;
    }
    /**
     * An abstract method detailing how a transaction may occur.
     * @param actor the actor trading with the trader
     */
    public abstract void transaction(Actor actor);
    /**
     * An abstract method for cloning the tradeAction.
     * @param item to insert to the clone method to give a new instance of the tradeAction
     */
    public abstract TradeAction clone(TradeItem item);
    /**
     * An abstract method that tells the result of a transaction.
     * @return a string telling the result to the player
     */
    public abstract String endTransaction(Actor actor);
}
