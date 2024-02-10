package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.talkactions.TalkAction;
import game.actors.PlayerCapability;
import game.interfaces.AbleToTalkInterface;
import game.interfaces.NextToInterface;

/**
 * When a trader has this behaviour, the trader checks if the player is too far away and set isTalking
 * to false through TalkAction.
 * Created by:
 * @author Nigel Tan
 * Modified by:
 * @author Nigel Tan
 */
public class TalkingBehaviour implements Behaviour, NextToInterface {
    private AbleToTalkInterface talkableActor;

    /**
     * Constructor for TalkingBehaviour.
     * @param talkableActor the actor to manipulate its isTalking attribute
     */
    public TalkingBehaviour(AbleToTalkInterface talkableActor){
        this.talkableActor = talkableActor;
    }

    /**
     * Checks each exit of the talkableActor and check if the player is in one of them.
     * @param map the GameMap
     * @param actor the location of the talkableActor
     * @return true if the player is nearby, false otherwise
     */
    @Override
    public boolean checkAdjacent(GameMap map, Actor actor){
        for(Exit exits:map.locationOf(actor).getExits()){
            Location adjacent = exits.getDestination();
            if(adjacent.containsAnActor() && adjacent.getActor().hasCapability(PlayerCapability.IS_PLAYER)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks each exit of the talkableActor through checkAdjacent and if the isTalking attribute is true.
     * If the player is too far away, a new TalkAction to make the trader stop talking else null.
     * @param map the GameMap
     * @param actor the location of the talkableActor
     * @return a TalkAction if the player is not nearby to set it to false.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!(checkAdjacent(map, actor)) && talkableActor.getIsTalking()){ //Too far away from trader
            return new TalkAction(talkableActor, false);
        }
        return null;
    }
}
