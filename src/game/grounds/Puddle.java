package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.abilities.Ability;
import game.actions.attributeactions.DrinkAction;
import game.actors.PlayerCapability;

/**
 * The Puddle class represents a type of ground called "Puddle" in the game world.
 * It extends the Ground class and adds the ability to be drinkable by players.
 *
 * The puddle can be interacted with by players to drink water from it.
 * It is denoted by the '~' character on the game map.
 *
 * Created by: Riordan D. Alfredo
 * Modified by: Nigel Tan, Sam Leong
 *
 */
public class Puddle extends Ground {
    /**
     * Constructor for the Puddle class.
     * Initializes a puddle on the game map and adds the DRINKABLE capability to it.
     * The puddle is represented by the '~' character on the map.
     */
    public Puddle() {

        super('~');
        this.addCapability(Ability.DRINKABLE);
    }

    /**
     * Determines the allowable actions that can be performed on the puddle at a specific location.
     * Players can drink from the puddle if they are present at the location.
     *
     * @param actor      The actor interacting with the puddle.
     * @param location   The location of the puddle.
     * @param direction  The direction from which the interaction is happening.
     * @return A list of allowable actions, including drinking from the puddle if the player is present.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (location.containsAnActor() && location.getActor().hasCapability(PlayerCapability.IS_PLAYER)) {
            actions.add(new DrinkAction(this));
        }
        return actions;
    }

    /**
     * Returns a string representation of the puddle, which is "Puddle".
     *
     * @return The string representation of the puddle.
     */
    @Override
    public String toString() {
        return "Puddle";
    }
}
