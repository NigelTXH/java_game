package game.actors.blacksmith;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.talkactions.TalkAction;
import game.actions.talkactions.TalkingToPlayer;
import game.behaviours.TalkingBehaviour;
import game.interfaces.AbleToTalkInterface;
import game.interfaces.TalkingInterface;

import java.util.HashMap;
import java.util.Map;
/**
 * Abstract class representing a blacksmith, who stores UpgradableActions for the player to choose from.
 * All blacksmiths extending from this class can check if they are talking to the player.
 * Created by:
 * @author Nigel Tan
 */
public abstract class BlackSmith extends Actor implements TalkingInterface, AbleToTalkInterface {
    private boolean isTalking = false;
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor for BlackSmith.
     * @param name        Name of actor
     * @param displayChar Character to represent the actor
     * @param hitPoints   Actor's starting number of hitpoints
     */
    public BlackSmith(String name, char displayChar, int hitPoints) {
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
     * The blacksmith can talk to the player
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

        return actions;
    }

}
