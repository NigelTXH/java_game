package game.upgrade;

import game.buffactions.BuffAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Use this enum to represent the possible upgrades
 * @author Nigel Tan
 */
public enum Upgradable {
    /**
     * The upgrade for hit rate only.
     */
    HITRATE(BuffAttributes.HITRATE),
    /**
     * The upgrade for damage only.
     */
    ATTACK(BuffAttributes.ATTACK),
    /**
     * The upgrade for health only.
     */
    HEALTH(BuffAttributes.HEALTH),
    /**
     * The upgrade for stamina only.
     */
    STAMINA(BuffAttributes.STAMINA);

    private final List<BuffAttributes> upgradableStats = new ArrayList<>();

    /**
     * Constructor for Upgradable.
     * @param buffAttributes The possible attributes that can be buffed, stored into the enum
     */
    Upgradable(BuffAttributes... buffAttributes){
        for(BuffAttributes buffAttribute: buffAttributes){
            upgradableStats.add(buffAttribute);
        }
    }

    /**
     * Getter for the list of upgradableStats.
     * @return A copy of the upgradableStats list
     */
    public List<BuffAttributes> getUpgradableStats() {
        return Collections.unmodifiableList(upgradableStats);
    }
}
