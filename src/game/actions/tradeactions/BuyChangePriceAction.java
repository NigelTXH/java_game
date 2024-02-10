package game.actions.tradeactions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.trademechanic.TradeItem;

/**
 * A class used to trade with the player and change the buying price if possible
 * @author Nigel Tan
 */
public class BuyChangePriceAction extends BuyAction {

    private int priceChange;
    private Display display = new Display();

    /**
     * Constructor for BuyChangePriceAction.
     * @param price        price of item
     * @param chance chance of the special event occurring
     * @param tradeItem   the item to be traded
     * @param priceChange the price to change to
     */
    public BuyChangePriceAction(int price, int chance, TradeItem tradeItem, int priceChange){
        super(price, chance, tradeItem);
        this.priceChange = priceChange;

    }

    /**
     * When the player picks this option, this action will be executed where if the RNG is successful, the price changes
     * and adds the item to the player's inventory.
     * @param actor the actor the trader is trading with
     * @param map the GameMap
     * @return the end transaction from SellAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(this.checkRNG()){
            display.println("The trader decides to chance the price");
            this.setPrice(priceChange);
        }
        this.transaction(actor);
        if(isPaid()){
            this.addToInventory(actor);
        }
        return this.endTransaction(actor);

    }

    /**
     * A method for cloning the BuyChangePriceAction.
     * @param item to insert to the clone method to give a new instance of BuyChangePriceAction
     */
    @Override
    public BuyAction clone(TradeItem item){
        return new BuyChangePriceAction(this.getPrice(), this.getChance(), item, priceChange);
    }


}
