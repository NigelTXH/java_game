package game.actions.talkactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.AbleToTalkInterface;

/**
 * A class used to talk to the player
 * @author Nigel Tan
 */
public class TalkAction extends Action {

    private AbleToTalkInterface talkableActor;
    private boolean condition;

    /**
     * Constructor for TalkAction.
     * @param talkableActor the actor to change the isTalking attribute
     * @param condition the condition to set the isTalking attribute to
     */
    public TalkAction(AbleToTalkInterface talkableActor, boolean condition){
        this.talkableActor = talkableActor;
        this.condition = condition;
    }

    /**
     * A method executed by the player if this action is chosen to set the
     * talkableActor's isTalking attribute to whatever condition is set.
     * @param actor the actor performing this action
     * @param map the GameMap
     * @return the menu description.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        talkableActor.talkingTo(condition);
        return menuDescription(actor);
    }

    /**
     * Gives 2 possible answers depending on if the condition is fulfilled.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player picks up the rock"
     */
    @Override
    public String menuDescription(Actor actor) {
        if(condition){
            return actor + " talks to " + talkableActor;
        }
        return talkableActor + " sees you off";
    }
}
