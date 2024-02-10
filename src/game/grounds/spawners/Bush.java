package game.grounds.spawners;

import game.interfaces.CanClone;

/**
 * The Bush class represents a type of terrain called "Bush" in the game world.
 * Bushes can spawn certain types of monsters. The spawn chance for monsters in bushes is customizable.
 *
 * The Bush class extends the Spawners class and specifies the display character for bushes and the default spawn chance.
 *
 * @author Sam Leong
 */
public class Bush extends Spawners{

    /**
     * Constructor for the Bush class.
     * Initializes a bush terrain that can spawn a specific type of monster with a specified spawn chance.
     *
     * @param monster The prototype of the monster that can spawn in bushes.
     */
    public Bush(CanClone monster, int spawnChance) {
        super('m', monster, spawnChance);
    }

}
