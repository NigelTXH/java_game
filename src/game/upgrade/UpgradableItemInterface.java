package game.upgrade;

import game.actions.blacksmithactions.UpgradableActions;
import game.actors.blacksmith.BlackSmithEnum;
import game.buffactions.BuffAttributes;

import java.util.List;

/**
 * An interface used if an item is upgradable.
 */
public interface UpgradableItemInterface {
    /**
     * Check if the blacksmith is nearby and the actor has the Talking to player capability.
     * @param blackSmithEnum the blacksmith to check if it is nearby
     * @return true if the blacksmith is nearby and the actor has the Talking to player capability.
     *         false otherwise
     */
    boolean findBlackSmithToTalkTo(BlackSmithEnum blackSmithEnum);

    /**
     * Increment the level attribute by 1
     */
    void levelUp();

    /**
     * Increase the value of the buffAttribute key by an amount to represent
     * a certain stat such as attack increasing.
     * @param buffAttributes the stat to increase
     */
    void raiseBuffAttributeBy(BuffAttributes buffAttributes, float amount);

    /**
     * Check if the item has the buffAttribute which it can increase its stats by
     * @param buffAttributes the stat to increase
     * @return true if the buffAttribute matches with a key from the upgradableStats list
     */
    boolean checkItemHasAttribute(BuffAttributes buffAttributes);

    /**
     * Getter method to get the level attribute
     * @return the level attribute
     */
    int getLevel();

    /**
     * Add an upgradableAction to the upgradableActionsList
     * @param upgradableAction the upgradable action to add
     */
    void addToUpgradableActionsList(UpgradableActions upgradableAction);

    /**
     * Getter method for upgradableActionsList
     * @return a copy of the upgradableActionsList
     */
    List<UpgradableActions> getUpgradableActionsList();
}
