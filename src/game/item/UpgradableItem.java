package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.blacksmithactions.UpgradableActions;
import game.actions.talkactions.TalkingToPlayer;
import game.actors.blacksmith.BlackSmithEnum;
import game.buffactions.BuffAttributes;
import game.interfaces.CanBeDroppedItem;
import game.upgrade.Upgradable;
import game.upgrade.UpgradableItemInterface;

import java.util.*;

/**
 * An abstract class for an upgradable item
 * @author Nigel Tan
 */
public abstract class UpgradableItem extends Item implements CanBeDroppedItem, UpgradableItemInterface {
    private Map<BuffAttributes,Float> upgradableStats = new HashMap<>();
    private int level;;
    private float upgradeAmount;
    private final int dropRate;
    private Location location;
    private List<UpgradableActions> upgradableActionsList = new ArrayList<>();

    /**
     * The constructor for UpgradableItem class
     * @param dropRate the drop rate of the item
     * @param name the name of the item
     * @param displayChar the displa char for the item
     * @param upgradable what buff attribute is upgradable
     */
    public UpgradableItem(int dropRate, String name, char displayChar, Upgradable upgradable){
        super(name, displayChar, true);
        this.dropRate = dropRate;

        for(BuffAttributes buffAttribute: upgradable.getUpgradableStats()){
            upgradableStats.put(buffAttribute, 0f);
        }
    }

    /**
     * Getter method to get the level attribute
     * @return the level attribute
     */
    @Override
    public int getLevel() {
        return level;
    }

    /**
     * Getter method to get the upgradeAmount attribute
     * @return the upgrade amount attribute
     */
    public float getUpgradeAmount() {
        return upgradeAmount;
    }

    /**
     * Method for checking the location of the item every tick.
     * @param currentLocation the location of this item.
     * @param actor owner of the item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        location = currentLocation;
    }

    /**
     * Getter method for the drop rate.
     * @return the drop rate
     */
    @Override
    public int getDropRate() {
        return this.dropRate;
    }

    /**
     * Method for adding the item to the current location.
     * @param location the location to drop the item
     */
    @Override
    public void addItemToGround(Location location) {
        location.addItem(this);
    }

    /**
     * What should be printed instead by default.
     * Only difference is that it shows how much it has been upgraded by.
     * @return the item and the level
     */
    @Override
    public String toString(){
        return super.toString() + " +" + level;
    }

    /**
     * Increment the level attribute by 1
     */
    @Override
    public void levelUp() {
        level++;
    }

    /**
     * Add an upgradableAction to the upgradableActionsList
     * @param upgradableAction the upgradable action to add
     */
    @Override
    public void addToUpgradableActionsList(UpgradableActions upgradableAction){
        upgradableActionsList.add(upgradableAction);
    }

    /**
     * Getter method for upgradableActionsList
     * @return a copy of the upgradableActionsList
     */
    @Override
    public List<UpgradableActions> getUpgradableActionsList() {
        return Collections.unmodifiableList(upgradableActionsList);
    }

    /**
     * Increase the value of the buffAttribute key by an amount to represent
     * a certain stat such as attack increasing.
     * @param buffAttributes the stat to increase
     */
    @Override
    public void raiseBuffAttributeBy(BuffAttributes buffAttributes, float amount) {
        upgradeAmount += amount;
        upgradableStats.put(buffAttributes, upgradableStats.get(buffAttributes) + upgradeAmount);
    }

    /**
     * Check if the item has the buffAttribute which it can increase its stats by
     * @param buffAttributes the stat to increase
     * @return true if the buffAttribute matches with a key from the upgradableStats list
     */
    @Override
    public boolean checkItemHasAttribute(BuffAttributes buffAttributes) {
        return upgradableStats.containsKey(buffAttributes);
    }

    /**
     * Check if the blacksmith is nearby and the actor has the Talking to player capability.
     * @param blackSmithEnum the blacksmith to check if it is nearby
     * @return true if the blacksmith is nearby and the actor has the Talking to player capability.
     *         false otherwise
     */
    @Override
    public boolean findBlackSmithToTalkTo(BlackSmithEnum blackSmithEnum){
        List<Exit> surroundings = location.getExits();

        for(Exit exit: surroundings){
            if(exit.getDestination().containsAnActor()){
                Actor actor = exit.getDestination().getActor();
                if(actor.hasCapability(blackSmithEnum) && actor.hasCapability(TalkingToPlayer.TALKING_TO_PLAYER)){
                    return true;
                }
            }
        }

        return false;
    }
}
