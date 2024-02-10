package game.droprate;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class used to collect information of the item and the drop rate
 * @author Nigel Tan
 */
public class DropItem {
    private final Item item;
    private final int dropRate;
    /**
     * Constructor for DropItem.
     * @param item the item to be dropped.
     * @param dropRate chance to drop out of 100.
     */
    public DropItem(Item item, int dropRate){
        this.item = item;
        this.dropRate = dropRate;
        item.addCapability(ItemDrop.ITEMDROP);
    }
    /**
     * Method to find the drop rate.
     * @param item the item to be dropped.
     * @return true if the actor and the item belongs to this DropItem instance.
     */
    public boolean findDropRateOfItem(Item item){
        return (this.item == item);
    }

    /**
     * Method which get the drop rate.
     * @return the drop rate name is returned.
     */
    public int getDropRate() {
        return dropRate;
    }

    /**
     * Method to add an item to the ground.
     * @param location the location to drop the item.
     */
    public void addItemToGround(Location location){
        location.addItem(item);
    }
}
