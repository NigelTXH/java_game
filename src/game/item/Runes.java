package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpRunesAction;
import game.interfaces.CanBeDroppedItem;

/**
 * The Runes class represents a special item called "Runes" in the game world.
 * Runes can be picked up by actors, and they have a specified quantity.
 * This class extends the Item class and provides a custom PickUpRunesAction for picking up runes.
 *
 * @author Sam Leong
 */
public class Runes extends Item implements CanBeDroppedItem {

    /**
     * Constructor for the Runes class.
     * Initializes a set of runes with a specified quantity.
     *
     * @param quantity The quantity of runes in the set.
     */
    private final int quantity;

    private final int dropRate;

    /***
     * Constructor.
     */
    public Runes(int dropRate, int quantity) {
        super("Runes", '$', true);
        this.quantity = quantity;
        this.dropRate = dropRate;
    }

    /**
     * Retrieves the PickUpAction associated with picking up these runes by an actor.
     *
     * @param actor The actor attempting to pick up the runes.
     * @return A PickUpRunesAction representing the action of picking up the runes.
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        return new PickUpRunesAction(this.quantity, this);
    }

    @Override
    public int getDropRate() {
        return this.dropRate;
    }

    @Override
    public void addItemToGround(Location location) {
        location.addItem(this);
    }
}
