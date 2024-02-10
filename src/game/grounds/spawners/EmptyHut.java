package game.grounds.spawners;

import game.interfaces.CanClone;

/**
 * The EmptyHut class represents a type of terrain called "Empty Hut" in the game world.
 * Empty huts can spawn certain types of monsters. The spawn chance for monsters in empty huts is customizable.
 *
 * The EmptyHut class extends the Spawners class and specifies the display character for empty huts and the default spawn chance.
 *
 * @author Sam Leong
 */
public class EmptyHut extends Spawners{

    /**
     * Constructor for the EmptyHut class.
     * Initializes an empty hut terrain that can spawn a specific type of monster with a specified spawn chance.
     *
     * @param monster The prototype of the monster that can spawn in empty huts.
     */
    public EmptyHut(CanClone monster, int spawnChance) {
        super('h', monster,  spawnChance);
    }
}
