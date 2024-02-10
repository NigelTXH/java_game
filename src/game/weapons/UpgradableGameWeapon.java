package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import game.abilities.CreateAbility;
import game.actions.blacksmithactions.UpgradableActions;
import game.actions.talkactions.TalkingToPlayer;
import game.actors.blacksmith.BlackSmithEnum;
import game.buffactions.BuffAttributes;
import game.upgrade.Upgradable;
import game.upgrade.UpgradableItemInterface;

import java.util.*;

/**
 * An abstract class for an upgradable weapon for the game
 * @author Nigel Tan
 */
public class UpgradableGameWeapon extends GameWeapon implements UpgradableItemInterface {

    private Map<BuffAttributes,Float> upgradableStats = new HashMap<>();
    private int level;
    private List<UpgradableActions> upgradableActionsList = new ArrayList<>();

    /**
     * Constructor for UpgradableGameWeapon without an ability.
     * @param name name of weapon
     * @param displayChar char to be displayed on the map
     * @param damage the damage to be dealt
     * @param verb a verb such as punch or kick
     * @param hitRate the chance to hit
     * @param upgradable what can be upgraded
     */
    public UpgradableGameWeapon(String name, char displayChar, int damage, String verb, int hitRate, Upgradable upgradable){
        super(name, displayChar, damage, verb, hitRate);

        for(BuffAttributes buffAttribute: upgradable.getUpgradableStats()){
            upgradableStats.put(buffAttribute, 0f);
        }
    }

    /**
     * Constructor for UpgradableGameWeapon with an ability.
     * @param name name of weapon
     * @param displayChar char to be displayed on the map
     * @param damage the damage to be dealt
     * @param verb a verb such as punch or kick
     * @param hitRate the chance to hit
     * @param ability the ability
     * @param upgradable what can be upgraded
     */
    public UpgradableGameWeapon(String name, char displayChar, int damage, String verb, int hitRate, CreateAbility ability, Upgradable upgradable){
        super(name, displayChar, damage, verb, hitRate, ability);

        for(BuffAttributes buffAttribute: upgradable.getUpgradableStats()){
            upgradableStats.put(buffAttribute, 0f);
        }
    }

    /**
     * Accessor for damage done by this weapon.
     * Only difference is that it is added by how much damage it has been upgraded by.
     *
     * @return the damage
     */
    @Override
    public int damage(){
        if(checkItemHasAttribute(BuffAttributes.ATTACK)) {
            return super.damage() + Math.round(upgradableStats.get(BuffAttributes.ATTACK));
        }

        return super.damage();
    }

    /**
     * Returns the chance to hit the target in integer.
     * Only difference is that it is added by how much chance it has been upgraded by.
     * @return the chance to hit
     */
    @Override
    public int chanceToHit(){
        if(checkItemHasAttribute(BuffAttributes.HITRATE)){
            return super.chanceToHit() + Math.round(upgradableStats.get(BuffAttributes.HITRATE));
        }

        return super.chanceToHit();
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
     * Increment the level attribute by 1
     */
    @Override
    public void levelUp() {
        level++;
    }

    /**
     * Increase the value of the buffAttribute key by an amount to represent
     * a certain stat such as attack increasing.
     * @param buffAttributes the stat to increase
     */
    @Override
    public void raiseBuffAttributeBy(BuffAttributes buffAttributes, float amount) {
        upgradableStats.put(buffAttributes, upgradableStats.get(buffAttributes) + amount);
    }

    /**
     * Check if the item has the buffAttribute which it can increase its stats by
     * @param buffAttributes the stat to increase
     * @return true if the buffAttribute matches with a key from the upgradableStats list
     */
    @Override
    public boolean checkItemHasAttribute(BuffAttributes buffAttributes) {
        if(upgradableStats == null){
            return false;
        }
        return upgradableStats.containsKey(buffAttributes);
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
     * Check if the blacksmith is nearby and the actor has the Talking to player capability.
     * @param blackSmithEnum the blacksmith to check if it is nearby
     * @return true if the blacksmith is nearby and the actor has the Talking to player capability.
     *         false otherwise
     */
    @Override
    public boolean findBlackSmithToTalkTo(BlackSmithEnum blackSmithEnum){
        List<Exit> surroundings = this.getLocationExits();

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
