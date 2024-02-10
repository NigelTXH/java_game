package game.actions.tradeactions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.trademechanic.TradeItem;

/**
 * A class used to trade with the player and not give the player the item.
 * @author Nigel Tan
 */
public class BuyCanTakeAction extends BuyAction {
    private Display display = new Display();

    /**
     * Constructor for BuyCanTakeAction.
     * @param price        price of item
     * @param chance chance of the special event occurring
     * @param tradeItem   the item to be traded
     */
    public BuyCanTakeAction(int price, int chance, TradeItem tradeItem){
        super(price, chance, tradeItem);
    }

    /**
     * When the player picks this option, this action will be executed where if the RNG is successful, the trader
     * does not give the item to the player and simply takes the player's money.
     * @param actor the actor the trader is trading with
     * @param map the GameMap
     * @return the end transaction from SellAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.transaction(actor);

        if(this.isPaid()){
            if(this.checkRNG()){
                display.println("The trader decides to scam you");
            }
            else{
                this.addToInventory(actor);
            }
        }

        return this.endTransaction(actor);

    }

    /**
     * A method for cloning the BuyCanTakeAction.
     * @param item to insert to the clone method to give a new instance of the BuyCanTakeAction
     */
    @Override
    public TradeAction clone(TradeItem item){
        return new BuyCanTakeAction(this.getPrice(), this.getChance(), item);
    }

}
