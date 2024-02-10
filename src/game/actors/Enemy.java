package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.droprate.DropItem;
import game.interfaces.CanBeDroppedItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * An abstract class representing actors that can drop. Any classes that extends from this class
 * are able to have access to a new list of DropItems for the actor to drop.
 * @author Nigel Tan
 */
public abstract class Enemy extends Actor {
    public Enemy(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
    }

    private final List<CanBeDroppedItem> dropItemList = new ArrayList<>();

    public void addDroppableItem(CanBeDroppedItem dropItem){
        dropItemList.add(dropItem);
    }

    /**
     * Override the original to drop the items if possible before removing the actor.
     * @param actor the perpetrator.
     * @param map where the actor fell unconscious
     * @return the statement from the super method of super.unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        dropItems(map.locationOf(this));
        return super.unconscious(actor, map);
    }

    /**
     * Go through all the dropItem in the list and if the rng succeeds, add the item to the ground.
     * @param location the location to drop the item
     */
    public void dropItems(Location location){
        for(CanBeDroppedItem dropItem : this.getDropItemList()){
            int dropRate = dropItem.getDropRate();
            if(dropRate != -1){
                Random random = new Random();
                if(random.nextInt(1,100) <= dropRate){
                    dropItem.addItemToGround(location);
                }
            }
        }
    }
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }


    /**
     * Getter method for dropItemList.
     * @return an unmodifiable list of dropItemList.
     */
    public List<CanBeDroppedItem> getDropItemList() {
        return Collections.unmodifiableList(dropItemList);
    }
}
