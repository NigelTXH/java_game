package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.actors.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Nigel Tan
 */
public class Floor extends Ground {
    /**
     * Constructor for floor.
     */
    public Floor() {
        super('_');
    }
    /**
     * Returns true if an Actor has a status that is hostile to enemies else any other actors cannot enter.
     * @param actor the Actor who might be moving
     * @return true if the Actor can enter this location
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if(actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            return true;
        }
        return false;
    }
}
