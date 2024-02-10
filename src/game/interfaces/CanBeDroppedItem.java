package game.interfaces;

import edu.monash.fit2099.engine.positions.Location;

public interface CanBeDroppedItem {

    public int getDropRate();

    public void addItemToGround(Location location);
}
