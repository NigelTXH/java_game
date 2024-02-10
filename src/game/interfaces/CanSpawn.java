package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
/**
 * An interface used to spawn any monster
 * @author Nigel Tan
 */
public interface CanSpawn {
    /**
     * Method for if a monster can be spawned.
     * @param location the location of this graveyard
     * @param monster the monster to check if can be spawned at that location
     */
    void canSpawnMonster(Location location, Actor monster);
    /**
     * Method for how a monster is spawned.
     * @param location the location of this graveyard
     */
    void spawnMechanic(Location location);

}
