package game.grounds.spawners;

import game.interfaces.CanClone;
/**
 * A class used to implement the graveyard
 * @author Nigel Tan
 */
public class Graveyard extends Spawners{
    /**
     * Constructor for Graveyard.
     */
    public Graveyard(CanClone monster) {
        super('n', monster, 25);
    }

}
