package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actors.Status;

/**
 * A class used to implement the attack behaviour of an npc
 * @author Nigel Tan
 */
public class AttackBehavior implements Behaviour {

    /**
     * Returns an AttackAction to attack the player, if possible.
     * If no attack is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exits : map.locationOf(actor).getExits()) {
            if(map.isAnActorAt(exits.getDestination())){
                Actor otherActor= exits.getDestination().getActor();
                if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return new AttackAction(otherActor, exits.getName());
                }

            }

        }
        return null;
    }
}
