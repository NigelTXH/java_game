package game.item.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.attributeactions.IncreaseMaxHealthAction;
import game.interfaces.CanBeDroppedItem;
import game.interfaces.CanClone;

public class BloodBerry extends Item implements CanBeDroppedItem {
    private int dropRate;
    /***
     * Constructor.
     */
    public BloodBerry(int dropRate) {
        super("Blood Berry", '*', true);
        this.dropRate = dropRate;
    }

    @Override
    public ActionList allowableActions(Actor owner) {
        return new ActionList(new IncreaseMaxHealthAction(5, this));
    }

    @Override
    public String toString() {
        return "Blood Berry";
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
