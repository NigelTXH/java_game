package game.actors.ancientwoodactors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.talkactions.TalkAction;
import game.upgrade.Upgradable;
import game.trademechanic.TradeItem;
import game.abilities.FocusAbility;
import game.actions.tradeactions.*;
import game.actors.Player;
import game.actors.Trader;
import game.item.consumables.HealingVial;
import game.item.consumables.StaminaVial;
import game.weapons.Sword;
/**
 * Class representing the Isolated Traveller, who is a trader that the player can talk and trade with.
 * Created by:
 * @author Nigel Tan
 * Modified by:
 * @author Nigel Tan
 */
public class IsolatedTraveller extends Trader {
    /**
     * The constructor of the Isolated Traveller class. Trade actions are added here
     * @param player The player to follow
     */
    public IsolatedTraveller(Player player){
        super("Isolated Traveller", 'ඞ', 200);
        HealingVial healingVial = new HealingVial(25);
        TradeItem healingVialTrade = new TradeItem(healingVial);

        this.addTradeItem(new BuyChangePriceAction(100,25,healingVialTrade,Math.round(100*1.5f)));
        this.addTradeItem(new SellChangePriceAction(35,10,healingVialTrade,35*2));

        //not sure what drop rate to put here, using 100 as a placeholder
        StaminaVial staminaVial = new StaminaVial(100);
        TradeItem staminaVialTrade = new TradeItem(staminaVial);
        this.addTradeItem( new BuyChangePriceAction(75,10,staminaVialTrade,Math.round(100*0.8f)));
        this.addTradeItem( new ScamTakeAction(25,50,staminaVialTrade));

        FocusAbility focus = new FocusAbility(5,0.1f,90f, player.getAttributeMaximum(BaseActorAttributes.STAMINA)*0.2f);
        Sword broadsword = new Sword("broad sword", '1', 110, 80, focus, Upgradable.ATTACK);
        TradeItem tradeBroadSword = new TradeItem(broadsword);
        this.addTradeItem(new BuyCanTakeAction(250,5,tradeBroadSword));
    }

    /**
     * The trader can talk and trade to the player
     *
     * @param otherActor the Actor that might perform the actions given
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of possible actions for the player
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();


        if(this.getIsTalking() == false){
            actions.add(new TalkAction(this, true));
            return actions;
        }

        for(TradeAction tradeAction : this.getTradeItemActionList()){
            actions.add(tradeAction);
        }

        return actions;
    }

}
