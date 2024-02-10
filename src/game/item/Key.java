package game.item;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.CanBeDroppedItem;
import game.world.AllGameMapsEnum;
/**
 * A class used to unlock gates
 * @author Nigel Tan
 */
public class Key extends Item implements CanBeDroppedItem {
    private int dropRate;
    /**
     * Constructor for Key.
     */
    public Key(int dropRate){
        super("Old Key", '-', true);
        this.addCapability(CanUnlock.CANUNLOCK);
        this.dropRate = dropRate;
    }
    /**
     * Method that adds a capability to represent that the key can unlock a certain location.
     * @param allGameMapsEnum the location that can be added
     */
    public void canUnlock(AllGameMapsEnum allGameMapsEnum){
        this.addCapability(allGameMapsEnum);
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
