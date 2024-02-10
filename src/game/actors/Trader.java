package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.talkactions.TalkingToPlayer;
import game.actions.tradeactions.TradeAction;
import game.behaviours.TalkingBehaviour;
import game.interfaces.AbleToTalkInterface;
import game.interfaces.TalkingInterface;

import java.util.*;

/**
 * Abstract class representing a trader, who stores list of trade actions that the player can perform.
 * All traders extending from this class can check if they are talking to the player as well as
 * store TradeActions.
 * Created by:
 * @author Nigel Tan
 */
public abstract class Trader extends Actor implements TalkingInterface, AbleToTalkInterface {
    private boolean isTalking = false;
    private List<TradeAction> tradeItemActionList = new ArrayList<>();
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    /**
     * Constructor for Trader.
     * @param name        Name of actor
     * @param displayChar Character to represent the actor
     * @param hitPoints   Actor's starting number of hitpoints
     */
    public Trader(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(999, new TalkingBehaviour(this));
    }
    /**
     * At each turn, check if the player is too far away to talk to.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the prioritised action that can be performed in that iteration or do nothing
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (int key : behaviours.keySet()) {
            Action action = behaviours.get(key).getAction(this, map);
            if(action != null){
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * A setter for the isTalking attribute and add the talking to player capability.
     * @param bool        set isTalking to true or false and also add or remove the capability
     */

    public void talkingTo(boolean bool) {
        if(bool){
            this.addCapability(TalkingToPlayer.TALKING_TO_PLAYER);
        }
        else{
            this.removeCapability(TalkingToPlayer.TALKING_TO_PLAYER);
        }
        isTalking = bool;
    }

    /**
     * A getter for the isTalking attribute.
     * @return a copy of the boolean.
     */
    public boolean getIsTalking(){
        boolean isTalkingCopy = isTalking;
        return isTalkingCopy;
    }

    /**
     * A method to add a tradeAction to tradeItemActionList.
     * @param tradeAction the action to be added to the list.
     */
    public void addTradeItem(TradeAction tradeAction){
        tradeItemActionList.add(tradeAction);
    }

    /**
     * A getter for the isTalking attribute.
     * @return a copy of the tradeItemActionList.
     */
    public List<TradeAction> getTradeItemActionList() {
        return Collections.unmodifiableList(tradeItemActionList);
    }
}
