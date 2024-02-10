package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Nigel Tan
 */
public class Wall extends Ground {
    /**
     * Constructor for wall.
     */
    public Wall() {
        super('#');
    }
    /**
     * A wall which no actor can enter.
     * @return false.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
