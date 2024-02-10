package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface used if something needs to happen when something is next to
 * @author Nigel Tan
 */
public interface NextToInterface {
    /**
     * An abstract method which returns a value while checking nearby.
     * @param map the GameMap
     * @param actor the location of the actor
     * @return true if a condition is met, false otherwise
     */
    boolean checkAdjacent(GameMap map, Actor actor);

}
