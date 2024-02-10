package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.displays.FancyMessage;
import game.actors.PlayerCapability;

/**
 * A class that represents bare dirt.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Nigel Tan
 */
public class Void extends Ground {

    private Display display = new Display();
    /**
     * Constructor for Void.
     */
    public Void() {
        super('+');
    }
    /**
     * Method for checking if the player is stepping on the void every turn.
     * @param location the location of this void
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()){
            Actor actor = location.getActor();
            if(actor.hasCapability(PlayerCapability.IS_PLAYER)){
                actor.unconscious(location.map());
                display.println("\n"+ FancyMessage.YOU_DIED);
                location.map().draw(display);
            }
        }

    }
}