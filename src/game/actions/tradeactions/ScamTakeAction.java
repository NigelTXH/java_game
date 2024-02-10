package game.actions.tradeactions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.trademechanic.TradeItem;

/**
 * A class used to trade with the player and not give to the player the item despite
 * taking the player's money.
 * @author Nigel Tan
 */
public class ScamTakeAction extends SellAction {
    private Display display = new Display();

    /**
     * Constructor for ScamTakeAction.
     * @param price        price of item
     * @param chance chance of the special event occurring
     * @param tradeItem   the item to be traded
     */
    public ScamTakeAction(int price, int chance, TradeItem tradeItem){
        super(price, chance, tradeItem);
    }

    /**
     * When the player picks this option, this action will be executed where if the RNG is successful, the trader
     * takes the item from the player without paying the player.
     * @param actor the actor the trader is trading with
     * @param map the GameMap
     * @return the end transaction from SellAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.transaction(actor);
        if(this.checkRNG()){
            display.println("The trader decides to scam you");
            this.setPrice(0);
        }
        actor.addBalance(this.getPrice());
        return this.endTransaction(actor);

    }

    /**
     * A method for cloning the ScamTakeAction.
     * @param item to insert to the clone method to give a new instance of the ScamTakeAction
     */
    @Override
    public TradeAction clone(TradeItem item){
        return new ScamTakeAction(this.getPrice(), this.getChance(), item);
    }

}
