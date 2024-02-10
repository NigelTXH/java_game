package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.abilities.CreateAbility;
import game.actions.AbilityAction;
import game.actions.AttackAction;
import game.actors.Status;

import java.util.List;

/**
 * An abstract class for the weapon of the game
 * @author Nigel Tan
 */
public abstract class GameWeapon extends WeaponItem {
    private final float originalDamageMultiplier;
    private final int originalHitRate;
    private final CreateAbility ability;
    private final AbilityAction abilityAction;
    private Location location;

    /**
     * Constructor for GameWeapon without an ability.
     * @param name name of weapon
     * @param displayChar char to be displayed on the map
     * @param damage the damage to be dealt
     * @param verb a verb such as punch or kick
     * @param hitRate the chance to hit
     */
    public GameWeapon(String name, char displayChar, int damage, String verb, int hitRate){
        super(name, displayChar, damage, verb, hitRate);
        this.originalHitRate = chanceToHit();
        this.originalDamageMultiplier = 1.0f;
        this.ability = null;
        this.abilityAction = null;

    }
    /**
     * Constructor for GameWeapon with an ability.
     * @param name name of weapon
     * @param displayChar char to be displayed on the map
     * @param damage the damage to be dealt
     * @param verb a verb such as punch or kick
     * @param hitRate the chance to hit
     * @param ability the ability
     */
    public GameWeapon(String name, char displayChar, int damage, String verb, int hitRate, CreateAbility ability){
        super(name, displayChar, damage, verb, hitRate);
        this.originalHitRate = chanceToHit();
        this.originalDamageMultiplier = 1.0f;
        this.ability = ability;
        this.abilityAction = new AbilityAction(this, ability);

        this.addCapability(ability.getAbilityName());

    }
    /**
     * Method to revert a buffed weapon
     */
    public void revert(){
        //A weapon can at most have 1 ability and all buffs related to the ability expires at onnce
        this.updateHitRate(originalHitRate);
        this.updateDamageMultiplier(originalDamageMultiplier);
    }
    /**
     * List of allowable actions possibly being empty, or if an ability is in the weapon, add an AbilityAction
     * to the list. Also checks who can be attacked.
     * @param owner the actor that owns the item
     * @return an unmodifiable list of Actions which either contains actions or empty list
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        if(ability!=null){
            actionList.add(abilityAction);
        }

        for(Exit exit: location.getExits()){
            Location destination = exit.getDestination();
            if(destination.containsAnActor()){
                Actor target = destination.getActor();
                if(target.hasCapability(Status.ENEMY)){
                    actionList.add(new AttackAction(target, exit.getName(), this));
                }
            }
        }

        return actionList;
    }

    /**
     * Method for reducing the duration of the weapon and reverting if it has expired and checking which enemy to attack.
     * @param currentLocation the location of this weapon.
     * @param actor owner of the weapon.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if(ability != null){
            ability.reduceDuration();
            if(ability.expire()){
                revert();
            }
        }


        this.location = currentLocation;
    }

    /**
     * Method for reverting the weapon's stat when it is on the ground.
     * @param currentLocation the location of this weapon.
     */
    @Override
    public void tick(Location currentLocation) {
        this.revert();
    }

    /**
     * Method for getting the possible exits for a location.
     * @return the exits for the item's location
     */
    public List<Exit> getLocationExits() {
        return location.getExits();
    }
}
